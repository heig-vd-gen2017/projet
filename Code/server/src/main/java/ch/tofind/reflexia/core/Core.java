package ch.tofind.reflexia.core;

import ch.tofind.reflexia.database.DatabaseManager;
import ch.tofind.reflexia.game.PlayerScore;
import ch.tofind.reflexia.errors.LobbyClosed;
import ch.tofind.reflexia.errors.UsernameTaken;
import ch.tofind.reflexia.game.GameManager;
import ch.tofind.reflexia.game.Player;
import ch.tofind.reflexia.mode.GameMode;
import ch.tofind.reflexia.mode.GameModeManager;
import ch.tofind.reflexia.mode.GameObject;
import ch.tofind.reflexia.network.MulticastClient;
import ch.tofind.reflexia.network.NetworkProtocol;
import ch.tofind.reflexia.network.Server;
import ch.tofind.reflexia.network.UnicastClient;
import ch.tofind.reflexia.mode.GameObjectRandomizer;
import ch.tofind.reflexia.utils.Logger;
import ch.tofind.reflexia.utils.Network;
import ch.tofind.reflexia.utils.Serialize;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class Core implements ICore {

    //! Logger for debugging.
    private static final Logger LOG = new Logger(Core.class.getSimpleName());

    //! Shared instance of the object for all the application.
    private static Core instance = null;

    //! Multicast client to send commands via multicast.
    private MulticastClient multicast;

    //! Unicast client to send commands via unicast.
    private UnicastClient client;

    //! The server.
    private Server server;

    //! The game manager.
    private GameManager gameManager;

    private GameObjectRandomizer gameObjectRandomizer;

    //! Porte used for unicast
    private int unicastPort;

    /**
     * Core single constructor. Avoid the instantiation.
     */
    private Core() {
        gameManager = GameManager.getInstance();
    }

    /**
     * Get the object instance.
     * @return The instance of the object.
     */
    public static Core getInstance() {

        if(instance == null) {
            synchronized (Core.class) {
                if (instance == null) {
                    instance = new Core();
                }
            }
        }

        return instance;
    }

    /**
     * sets the game mode
     * @param gameModeName The name of the game mode
     */
    public void setGameMode(String gameModeName) {

        GameMode gameMode = GameModeManager.getInstance().getGameModes().get(gameModeName);

        gameManager.setGameMode(gameMode);

        LOG.info("The game mode is set.");

    }

    /**
     * accepts a connection
     * @param multicastAddress The multicast address
     * @param multicastPortString The multicast port
     * @param ipAddressName The name of the IP address
     * @param unicastPortString The Unicast Port
     */
    public void acceptConnections(String multicastAddress, String multicastPortString, String ipAddressName, String unicastPortString) {

        InetAddress ipAddress = Network.getIPv4Interfaces().get(ipAddressName);

        unicastPort = Integer.valueOf(unicastPortString);

        int multicastPort = Integer.valueOf(multicastPortString);

        start(multicastAddress, multicastPort, ipAddress, unicastPort);

        LOG.info("Server is started.");

        gameManager.acceptPlayers();
    }

    /**
     * begins the game
     */
    public void beginGame() {

        LOG.info("The game begins.");

        // Multicast send BEGIN command
        multicast.send(ApplicationProtocol.BEGIN_GAME + NetworkProtocol.END_OF_LINE + NetworkProtocol.END_OF_COMMAND);

        GameMode gameMode = gameManager.getGameMode();

        gameObjectRandomizer = new GameObjectRandomizer(gameMode, multicast);

        new Thread(gameObjectRandomizer).start();
    }

    /**
     * ends the game
     */
    public void endGame() {

        LOG.info("The game ends.");

        if (multicast != null) {
            multicast.send(ApplicationProtocol.END_OF_GAME + NetworkProtocol.END_OF_LINE + NetworkProtocol.END_OF_COMMAND);
            multicast = null;
        }

        stop();
        LOG.info("The server is shutdown.");

        gameManager.reset();
        gameManager.refusePlayers();
    }

    /**
     * resets the scores
     * @param date The date at which to reset the scores
     */
    public void resetScores(Date date) {

        Session session = DatabaseManager.getInstance().getSession();

        Query deleteScoresBeforeDate = session.createQuery("DELETE PlayerScore WHERE date <= :date");

        deleteScoresBeforeDate.setParameter("date", date);

        DatabaseManager.getInstance().execute(deleteScoresBeforeDate);

        LOG.info("The scores are reset.");
    }

    /**
     * makes a player join the game
     * @param args Arguments of the command.
     * @return The JSON Game Mode
     */
    public String JOIN(ArrayList<Object> args) {

        args.remove(0); // Remove the socket as we don't need it

        String pseudo = (String) args.remove(0);

        try {
            gameManager.addPlayer(pseudo);
        } catch (LobbyClosed e) {
            return ApplicationProtocol.GAME_FULL + NetworkProtocol.END_OF_LINE +
                    NetworkProtocol.END_OF_COMMAND;
        } catch (UsernameTaken e) {
            return ApplicationProtocol.USERNAME_USED + NetworkProtocol.END_OF_LINE +
                    NetworkProtocol.END_OF_COMMAND;
        }

        GameMode gameMode = gameManager.getGameMode();

        String gameModeJson = Serialize.serialize(gameMode);

        return ApplicationProtocol.JOINED + NetworkProtocol.END_OF_LINE +
                gameModeJson + NetworkProtocol.END_OF_LINE +
                NetworkProtocol.END_OF_COMMAND;
    }

    /**
     * Get the player who touched the object, the object and its time.
     * @param args Arguments of the command.
     * @return The result of the command.
     */
    public String OBJECT_TOUCHED(ArrayList<Object> args) {

        args.remove(0); // We remove the socket as we don't need it.
        String playerPseudo = (String) args.remove(0);
        Integer objectId = Integer.valueOf((String) args.remove(0));

        GameObject gameObject = gameObjectRandomizer.getGeneratedGameObjects().get(objectId);

        if (gameObject.getType().equals("mystery")) {
            Random random = new Random();
            String[] types = {"bonus", "malus"};

            String type = types[random.nextInt(types.length)];

            gameObject = GameManager.getInstance().getGameMode().getGameObjects().get(type);

        }

        Map<String, Player> players = gameManager.getPlayers();

        Player currentPlayer = players.get(playerPseudo);

        currentPlayer.setScore(currentPlayer.getScore() + gameObject.getPoints());

        if (gameManager.isWinner(playerPseudo)) {

            String playersJson = Serialize.serialize(players);

            // Save all scores in database
            for (Player player : players.values()) {

                PlayerScore playerScore = new PlayerScore(player.getPseudo(),
                        gameManager.getGameMode().getName(),
                        player.getScore());

                DatabaseManager.getInstance().save(playerScore);

            }

            String command = ApplicationProtocol.WINNER + NetworkProtocol.END_OF_LINE +
                    playerPseudo + NetworkProtocol.END_OF_LINE +
                    playersJson + NetworkProtocol.END_OF_LINE +
                    NetworkProtocol.END_OF_COMMAND;

            if (multicast != null) {
                multicast.send(command);
            }
        }

        return ApplicationProtocol.SCORES_UPDATE + NetworkProtocol.END_OF_LINE +
                currentPlayer.getScore() + NetworkProtocol.END_OF_LINE +
                NetworkProtocol.END_OF_COMMAND;
    }

    /**
     * Start the server.
     * @param multicastAddress The multicast address to use for communication.
     * @param multicastPort The multicast port to use for communication.
     * @param interfaceToUse The network interface to use for multicast.
     * @param unicastPort The unicast address to use for communication.
     */
    @Override
    public void start(String multicastAddress, int multicastPort, InetAddress interfaceToUse, int unicastPort) {

        multicast = new MulticastClient(multicastAddress, multicastPort, interfaceToUse);

        server = new Server(unicastPort);

        new Thread(multicast).start();
        new Thread(server).start();
    }

    /**
     * Execute the command on the core.
     * @param command The command to execute.
     * @param args Arguments of the command. The arguments of the command.
     */
    @Override
    public String execute(String command, ArrayList<Object> args) {

        Method method;

        String result = "";

        try {
            method = this.getClass().getMethod( command, ArrayList.class);
            result = (String) method.invoke(this, args);
        } catch (NoSuchMethodException e) {
            // Do nothing
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Stop the server.
     */
    @Override
    public void stop() {
        if (!Objects.isNull(multicast)) {
            multicast.stop();
            multicast = null;
        }

        if (!Objects.isNull(gameObjectRandomizer)) {
            gameObjectRandomizer.stop();
        }

        if (!Objects.isNull(server)) {
            server.stop();
            server = null;
        }

        DatabaseManager.getInstance().close();
    }

    /**
     * Send message to hostname by unicast.
     * @param hostname Where to send the message.
     * @param message The message to send.
     */
    @Override
    public void sendUnicast(InetAddress hostname, int port, String message) {
        // Do nothing
    }

    /**
     * Send message by multicast.
     * @param message The message to send.
     */
    @Override
    public void sendMulticast(String message) {
        multicast.send(message);
    }
}
