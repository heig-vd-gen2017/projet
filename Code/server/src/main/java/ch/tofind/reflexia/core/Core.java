package ch.tofind.reflexia.core;

import ch.tofind.reflexia.game.GameManager;
import ch.tofind.reflexia.game.Player;
import ch.tofind.reflexia.mode.GameMode;
import ch.tofind.reflexia.mode.GameModeManager;
import ch.tofind.reflexia.network.MulticastClient;
import ch.tofind.reflexia.network.NetworkProtocol;
import ch.tofind.reflexia.network.Server;
import ch.tofind.reflexia.ui.ServerConfiguration;
import ch.tofind.reflexia.utils.Network;
import ch.tofind.reflexia.utils.Serialize;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

public class Core implements ICore {

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

        System.out.println("The game mode is set.");

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

        System.out.println("Server is started.");
    }

    /**
     * @brief begins the game
     */
    public void beginGame() {

        System.out.println("The game begins.");

        // Multicast send BEGIN command
        // multicast.send();

    }

    /**
     * @brief ends the game
     */
    public void endGame() {

        System.out.println("The game ends.");

        // Multicast send END_OF_GAME command
        //multicast.send();

        stop();
        System.out.println("The server is shutdown.");

    }

    /**
     * @brief resets the scores
     * @param date
     */
    public void resetScores(Date date) {

        System.out.println("The scores are reset.");

    }

    /**
     * @brief makes a player join the game
     * @param args
     * @return
     */
    public String JOIN(ArrayList<Object> args) {
        String pseudo = (String) args.remove(0);

        System.out.println(pseudo);

        Player player = new Player(pseudo, 0);

        GameManager.getInstance().addPlayer(player);

        int nbPlayers = GameManager.getInstance().getNumberOfPlayers();

        //ServerConfiguration.updateNbPlayer();

        System.out.println("Player '" + pseudo + "' has joined the game.");
        System.out.println("Number of players: " + nbPlayers);


        return "";
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
        }

        if (server != null) {
            server.stop();
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
