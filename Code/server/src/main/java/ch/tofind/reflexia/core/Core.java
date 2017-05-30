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

    public void setGameMode(String gameModeName) {

        GameMode gameMode = GameModeManager.getInstance().getGameModes().get(gameModeName);

        gameManager.setGameMode(gameMode);

        LOG.info("The game mode is set.");

    }

    public void acceptConnections(String multicastAddress, String multicastPortString, String ipAddressName, String unicastPortString) {

        InetAddress ipAddress = Network.getIPv4Interfaces().get(ipAddressName);

        int unicastPort = Integer.valueOf(unicastPortString);

        int multicastPort = Integer.valueOf(multicastPortString);

        start(multicastAddress, multicastPort, ipAddress, unicastPort);

        LOG.info("Server is started.");
    }

    public void beginGame() {

        LOG.info("The game begins.");

        // Multicast send BEGIN command
        // multicast.send();

    }

    public void endGame() {

        LOG.info("The game ends.");

        // Multicast send END_OF_GAME command
        //multicast.send();

        stop();
        LOG.info("The server is shutdown.");

    }

    public void resetScores(Date date) {

        Session session = DatabaseManager.getInstance().getSession();


        Query deleteScoresBeforeDate = session.createQuery("DELETE Score WHERE date < :date");
        deleteScoresBeforeDate.setParameter("date", date);

        DatabaseManager.getInstance().execute(deleteScoresBeforeDate);

        LOG.info("The scores are reset.");

    }

    public String JOIN(ArrayList<Object> args) {
        String pseudo = (String) args.remove(0);

        LOG.info(pseudo);

        Player player = new Player(pseudo, 0);

        GameManager.getInstance().addPlayer(player);

        int nbPlayers = GameManager.getInstance().getNumberOfPlayers();

        //ServerConfiguration.updateNbPlayer();

        LOG.info("Player '" + pseudo + "' has joined the game.");
        LOG.info("Number of players: " + nbPlayers);


        return "";
    }

    @Override
    public void start(String multicastAddress, int multicastPort, InetAddress interfaceToUse, int unicastPort) {

        multicast = new MulticastClient(multicastAddress, multicastPort, interfaceToUse);

        server = new Server(unicastPort);

        new Thread(multicast).start();
        new Thread(server).start();
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
        }

        if (server != null) {
            server.stop();
        }
    }

    @Override
    public void sendUnicast(InetAddress hostname, int port, String message) {
        // Do nothing
    }

    @Override
    public void sendMulticast(String message) {
        multicast.send(message);
    }
}
