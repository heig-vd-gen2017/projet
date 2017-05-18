package ch.tofind.reflexia.core;

import ch.tofind.reflexia.network.MulticastClient;
import ch.tofind.reflexia.network.NetworkProtocol;
import ch.tofind.reflexia.network.UnicastClient;
import ch.tofind.reflexia.utils.Serialize;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;

public class Core implements ICore {

    //! Shared instance of the object for all the application
    private static Core instance = null;

    //! Multicast client
    private MulticastClient multicast;

    //! Unicast client
    private UnicastClient client;

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

    public void connection(String pseudo, String multicastAddress, String multicastPortString, String ipAddressName, String unicastPortString) {

        InetAddress ipAddress = Serialize.unserialize(ipAddressName, InetAddress.class);

        int unicastPort = Integer.valueOf(unicastPortString);

        int multicastPort = Integer.valueOf(multicastPortString);

        start(multicastAddress, multicastPort, ipAddress, unicastPort);

        String command = ApplicationProtocol.JOIN + NetworkProtocol.END_OF_LINE +
                pseudo + NetworkProtocol.END_OF_LINE +
                NetworkProtocol.END_OF_COMMAND;

        sendUnicast(ipAddress, unicastPort, command);
    }

    public String END_OF_COMMUNICATION(ArrayList<Object> args) {
        System.out.println("End of communication client side.");
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
        multicast.stop();
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

    private void start(String multicastAddress, int port, InetAddress interfaceToUse) {

        multicast = new MulticastClient(multicastAddress, port, interfaceToUse);

        new Thread(multicast).start();

    }
}
