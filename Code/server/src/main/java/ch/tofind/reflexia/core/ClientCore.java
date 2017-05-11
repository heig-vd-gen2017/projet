package ch.tofind.reflexia.core;

import ch.tofind.reflexia.network.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientCore extends AbstractCore implements ICore {

    //!
    private MulticastClient multicast;

    //!
    private UnicastClient client;

    //!
    private Gson json;

    //! A FINIR / VOIR SI NÉCESSAIRE
    private Map<InetAddress, String> availableServers;

    public ClientCore(String multicastAddress, int port, InetAddress interfaceToUse) {
        multicast = new MulticastClient(multicastAddress, port, interfaceToUse);
        new Thread(multicast).start();

        json = new GsonBuilder().create();

        availableServers = new HashMap<>();
    }

    public Map<InetAddress, String> getAvailableServers() {
        return availableServers;
    }

    public String END_OF_COMMUNICATION(ArrayList<Object> args) {
        System.out.println("End of communication client side.");
        return "";
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
}
