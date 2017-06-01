package ch.tofind.reflexia.core;

import ch.tofind.reflexia.database.DatabaseManager;
import ch.tofind.reflexia.game.GameManager;
import ch.tofind.reflexia.game.Player;
import ch.tofind.reflexia.mode.GameMode;
import ch.tofind.reflexia.mode.GameModeManager;
import ch.tofind.reflexia.network.MulticastClient;
import ch.tofind.reflexia.network.NetworkProtocol;
import ch.tofind.reflexia.network.Server;
import ch.tofind.reflexia.ui.ServerConfiguration;
import ch.tofind.reflexia.utils.Logger;
import ch.tofind.reflexia.utils.Network;
import ch.tofind.reflexia.utils.Serialize;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

public class Core implements ICore {

    //! Logger for debugging.
    private static final Logger LOG = new Logger(Core.class.getSimpleName());

    //! Shared instance of the object for all the application.
    private static Core instance = null;

    //! Multicast client to send commands via multicast.
    MulticastClient multicast;

    //! The server.
    Server server;

    //! The game manager.
    GameManager gameManager = GameManager.getInstance();

    /**
     * @brief Core single constructor. Avoid the instantiation.
     */
    private Core() {

    }

    /**
     * @brief Get the object instance.
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
     * @brief sets the game mode
     * @param gameModeName
     */
    public void setGameMode(String gameModeName) {

        GameMode gameMode = GameModeManager.getInstance().getGameModes().get(gameModeName);

        gameManager.setGameMode(gameMode);

        LOG.info("The game mode is set.");

    }

    /**
     * @brief accepts a connection
     * @param multicastAddress
     * @param multicastPortString
     * @param ipAddressName
     * @param unicastPortString
     */
    public void acceptConnections(String multicastAddress, String multicastPortString, String ipAddressName, String unicastPortString) {

        InetAddress ipAddress = Network.getIPv4Interfaces().get(ipAddressName);

        int unicastPort = Integer.valueOf(unicastPortString);

        int multicastPort = Integer.valueOf(multicastPortString);

        start(multicastAddress, multicastPort, ipAddress, unicastPort);

        LOG.info("Server is started.");
    }

    /**
     * @brief begins the game
     */
    public void beginGame() {

        LOG.info("The game begins.");

        // Multicast send BEGIN command
        multicast.send(ApplicationProtocol.BEGIN_GAME + NetworkProtocol.END_OF_LINE + NetworkProtocol.END_OF_COMMAND);

    }

    /**
     * @brief ends the game
     */
    public void endGame() {

        LOG.info("The game ends.");

        if (multicast != null) {
            multicast.send(ApplicationProtocol.END_OF_GAME + NetworkProtocol.END_OF_LINE + NetworkProtocol.END_OF_COMMAND);
        }

        stop();
        LOG.info("The server is shutdown.");

    }

    /**
     * @brief resets the scores
     * @param date
     */
    public void resetScores(Date date) {

        Session session = DatabaseManager.getInstance().getSession();

        Query deleteScoresBeforeDate = session.createQuery("DELETE Score WHERE date <= :date");

        deleteScoresBeforeDate.setParameter("date", date);

        DatabaseManager.getInstance().execute(deleteScoresBeforeDate);

        LOG.info("The scores are reset.");

    }

    /**
     * @brief makes a player join the game
     * @param args
     * @return
     */
    public String JOIN(ArrayList<Object> args) {

        args.remove(0); // Remove the socket as we don't need it

        String pseudo = (String) args.remove(0);

        LOG.info(pseudo);

        Player player = new Player(pseudo, 0);

        GameManager.getInstance().addPlayer(player);

        int nbPlayers = GameManager.getInstance().getNumberOfPlayers();

        //ServerConfiguration.updateNbPlayer();

        LOG.info("Player '" + pseudo + "' has joined the game.");
        LOG.info("Number of players: " + nbPlayers);


        return ApplicationProtocol.JOINED + NetworkProtocol.END_OF_LINE +
                100 + NetworkProtocol.END_OF_LINE +
                NetworkProtocol.END_OF_COMMAND;
    }

    /**
     * @brief Start the server.
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
     * @brief Execute the command on the core.
     * @param command The command to execute.
     * @param args The arguments of the command.
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
     * @brief Stop the server.
     */
    @Override
    public void stop() {
        if (multicast != null) {
            multicast.stop();
            multicast = null;
        }

        if (server != null) {
            server.stop();
            server = null;
        }
    }

    /**
     * @brief Send message to hostname by unicast.
     * @param hostname Where to send the message.
     * @param message The message to send.
     */
    @Override
    public void sendUnicast(InetAddress hostname, int port, String message) {
        // Do nothing
    }

    /**
     * @brief Send message by multicast.
     * @param message The message to send.
     */
    @Override
    public void sendMulticast(String message) {
        multicast.send(message);
    }
}
