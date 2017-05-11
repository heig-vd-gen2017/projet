package ch.tofind.reflexia.core;

import ch.tofind.reflexia.network.MulticastClient;
import ch.tofind.reflexia.network.NetworkProtocol;
import ch.tofind.reflexia.network.Server;
import ch.tofind.reflexia.utils.Network;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Core implements ICore {

    //! Shared instance of the object for all the application
    private static Core instance = null;

    //! Multicast client to send commands via multicast.
    MulticastClient multicast;

    //! The server.
    Server server;

    private Core(String multicastAddress, int multicastPort, InetAddress interfaceToUse, int unicastPort) {

        multicast = new MulticastClient(multicastAddress, multicastPort, interfaceToUse);

        server = new Server(unicastPort);

        new Thread(multicast).start();
        new Thread(server).start();
    }

    /**
     * @brief Get the object instance
     * @return The instance of the object
     */
    public static Core getInstance() {

        if(instance == null) {
            synchronized (Core.class) {
                if (instance == null) {
                    instance = new Core(NetworkProtocol.MULTICAST_ADDRESS, NetworkProtocol.MULTICAST_PORT, Network.INTERFACE_TO_USE, NetworkProtocol.UNICAST_PORT);
                }
            }
        }

        return instance;
    }

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
    public void sendUnicast(InetAddress hostname, String message) {

    }

    @Override
    public void sendMulticast(String message) {
        multicast.send(message);
    }

    @Override
    public void stop() {
        multicast.stop();
        server.stop();
    }

    public String END_OF_COMMUNICATION(ArrayList<Object> args) {
        System.out.println("End of communication client side.");
        return "";
    }
}
