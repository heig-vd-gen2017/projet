package ch.tofind.reflexia.core;

import ch.tofind.reflexia.game.GameManager;
import ch.tofind.reflexia.mode.GameMode;
import ch.tofind.reflexia.mode.GameObject;
import ch.tofind.reflexia.mode.RandomGameObject;
import ch.tofind.reflexia.network.MulticastClient;
import ch.tofind.reflexia.network.NetworkProtocol;
import ch.tofind.reflexia.network.UnicastClient;
import ch.tofind.reflexia.ui.ClientGame;
import ch.tofind.reflexia.utils.Configuration;
import ch.tofind.reflexia.utils.Network;
import ch.tofind.reflexia.utils.Point;
import ch.tofind.reflexia.utils.Serialize;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Alert;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

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

    public void objectTouched(Integer objectId) {
        String command = ApplicationProtocol.OBJECT_TOUCHED + NetworkProtocol.END_OF_LINE +
                pseudo + NetworkProtocol.END_OF_LINE +
                objectId + NetworkProtocol.END_OF_LINE +
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

        gameManager.setActualScore(gameMode.getStartingScore());


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

        String randomGameObjectJson = (String) args.get(0);

        GameMode gameMode = gameManager.getGameMode();

        ClientGame clientGame = ClientGame.getController();

        RandomGameObject randomGameObject = Serialize.unserialize(randomGameObjectJson, RandomGameObject.class);

        String type = randomGameObject.getGameObject().getType();

        Point position = randomGameObject.getPoint();

        String gameObjectImagePath = System.getProperty("user.dir") + File.separator + Configuration.getInstance().get("MODES_PATH") + File.separator +
                gameMode.getName() + File.separator +
                type + ".png";

        if (type.equals("mystery")) {
            Random random = new Random();
            String[] types = {"bonus", "malus"};

            type = types[random.nextInt(types.length)];

        }

        GameObject gameObject = gameMode.getGameObjects().get(type);

        Integer gameObjectId = randomGameObject.getId();
        Integer gameObjectPosX = position.getX();
        Integer gameObjectPosY = position.getY();
        Integer gameObjectTimeout = gameObject.getTimeout();

        // Delegate work to JavaFX thread.
        Platform.runLater(() -> {
            clientGame.addObject(gameObjectId, gameObjectImagePath, gameObjectPosX, gameObjectPosY, gameObjectTimeout);
        });

        return "";
    }

    public String WINNER(ArrayList<Object> args) {

        String player = (String) args.remove(0);

        // We stop the game
        stop();

        ClientGame clientGame = ClientGame.getController();

        String message = "We have a winner ! Congrats " + pseudo + "!";
        Platform.runLater(() -> {
            clientGame.showAlert("End of game :)", message, Alert.AlertType.INFORMATION);
        });

        return "";
    }

    public String SCORES_UPDATE(ArrayList<Object> args) {

        args.remove(0);

        String scoreString = (String) args.remove(0);

        Integer score = Integer.valueOf(scoreString);

        // Delegate work to JavaFX thread.
        Platform.runLater(() -> {
            gameManager.setActualScore(score);
        });

        return NetworkProtocol.END_OF_COMMUNICATION + NetworkProtocol.END_OF_LINE +
                NetworkProtocol.END_OF_COMMAND;
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
