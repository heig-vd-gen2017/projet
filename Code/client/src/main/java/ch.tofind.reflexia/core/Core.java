package ch.tofind.reflexia.core;

import ch.tofind.reflexia.network.MulticastClient;
import ch.tofind.reflexia.network.NetworkProtocol;
import ch.tofind.reflexia.network.UnicastClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
    /*

    private Core(String multicastAddress, int port, InetAddress interfaceToUse) {

        multicast = new MulticastClient(multicastAddress, port, interfaceToUse);

        new Thread(multicast).start();

    }
     */

    public void connexion(String playerName, String hostname, String port) {

        System.out.println("Coucou");
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
        client = new UnicastClient(hostname, NetworkProtocol.UNICAST_PORT);
        new Thread(client).start();
        client.send(message);
    }

    @Override
    public void sendMulticast(String message) {
        multicast.send(message);
    }

    @Override
    public void stop() {
        multicast.stop();
    }

    public String END_OF_COMMUNICATION(ArrayList<Object> args) {
        System.out.println("End of communication client side.");
        return "";
    }
}
