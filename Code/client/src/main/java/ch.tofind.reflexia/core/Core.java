package ch.tofind.reflexia.core;

import ch.tofind.reflexia.game.GameManager;
import ch.tofind.reflexia.mode.GameMode;
import ch.tofind.reflexia.network.MulticastClient;
import ch.tofind.reflexia.network.NetworkProtocol;
import ch.tofind.reflexia.network.UnicastClient;
import ch.tofind.reflexia.utils.Network;
import ch.tofind.reflexia.utils.Serialize;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.InetAddress;
import java.util.ArrayList;

public class Core implements ICore {

    //! Shared instance of the object for all the application
    private static Core instance = null;

    //! Multicast client
    private MulticastClient multicast;

    //! Unicast client
    private UnicastClient client;

    //! Pseudo of the current player
    private String pseudo;

    //! Unicast port for communication
    private int unicastPort;

    //! Multicast address for communication
    private String multicastAddress;

    //! Multicast port for communication
    private int multicastPort;

    //! Network interface to use for communication
    private InetAddress networkInterface;

    //! IP address for the server
    InetAddress serverIpAddress;

    //! The game manager.
    private GameManager gameManager = GameManager.getInstance();

    private Core() {

    }

    /**
     * @brief Get the object instance
     * @return The instance of the object
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

    public void connection(String pseudo, String serverIPAddressString, String unicastPortString, String networkInterfaceString, String multicastAddress, String multicastPortString) {

        this.pseudo = pseudo;

        this.multicastAddress = multicastAddress;

        this.serverIpAddress = Serialize.unserialize(serverIPAddressString, InetAddress.class);

        this.networkInterface = Network.getIPv4Interfaces().get(networkInterfaceString);

        this.unicastPort = Integer.valueOf(unicastPortString);

        this.multicastPort = Integer.valueOf(multicastPortString);

        String command = ApplicationProtocol.JOIN + NetworkProtocol.END_OF_LINE +
                pseudo + NetworkProtocol.END_OF_LINE +
                NetworkProtocol.END_OF_COMMAND;


        sendUnicast(serverIpAddress, unicastPort, command);

    }

    public String END_OF_COMMUNICATION(ArrayList<Object> args) {
        System.out.println("End of communication client side.");
        return "";
    }

    public String GAME_FULL(ArrayList<Object> args) {
        System.out.println("Game full. Can't join it.");

        return NetworkProtocol.END_OF_COMMUNICATION + NetworkProtocol.END_OF_LINE +
                NetworkProtocol.END_OF_COMMAND;
    }

    public String USERNAME_USED(ArrayList<Object> args) {
        System.out.println("Username already taken.");

        return NetworkProtocol.END_OF_COMMUNICATION + NetworkProtocol.END_OF_LINE +
                NetworkProtocol.END_OF_COMMAND;
    }

    public String JOINED(ArrayList<Object> args) {
        System.out.println("You joined the game. Starting multicast.");

        args.remove(0); // Remove the socket as we don't need it

        String gameModeJson = (String) args.remove(0);

        GameMode gameMode = Serialize.unserialize(gameModeJson, GameMode.class);

        gameManager.setGameMode(gameMode);

        start(multicastAddress, multicastPort, networkInterface, unicastPort);

        return NetworkProtocol.END_OF_COMMUNICATION + NetworkProtocol.END_OF_LINE +
                NetworkProtocol.END_OF_COMMAND;
    }

    public String BEGIN_GAME(ArrayList<Object> args) {
        System.out.println("The game begins!");
        return "";
    }

    public String END_OF_GAME(ArrayList<Object> args) {
        System.out.println("The game ends, sadly...");
        return "";
    }

    public String SHOW_OBJECT(ArrayList<Object> args) {
        System.out.println("Object received: " + args.get(0));
        return "";
    }

    @Override
    public void start(String multicastAddress, int multicastPort, InetAddress interfaceToUse, int unicastPort) {
        multicast = new MulticastClient(multicastAddress, multicastPort, interfaceToUse);
        new Thread(multicast).start();
    }

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

    @Override
    public void stop() {
        if (multicast != null) {
            multicast.stop();
            multicast = null;
        }
    }

    @Override
    public void sendUnicast(InetAddress hostname, int port, String message) {
        client = new UnicastClient(hostname, port);
        new Thread(client).start();
        client.send(message);
    }

    @Override
    public void sendMulticast(String message) {
        multicast.send(message);
    }
}
