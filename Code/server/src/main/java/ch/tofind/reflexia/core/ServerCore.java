package ch.tofind.reflexia.core;

import ch.tofind.reflexia.network.MulticastClient;
import ch.tofind.reflexia.network.NetworkProtocol;
import ch.tofind.reflexia.network.Server;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ServerCore extends AbstractCore implements ICore {

    //! Name of the server.
    String name;

    //! Multicast client to send commands via multicast.
    MulticastClient multicast;

    //! The server.
    Server server;

    //!
    private Gson json;

    public ServerCore(String name, String multicastAddress, int multicastPort, InetAddress interfaceToUse, int unicastPort) {

        this.name = name;

        multicast = new MulticastClient(multicastAddress, multicastPort, interfaceToUse);

        server = new Server(unicastPort);

        new Thread(multicast).start();
        new Thread(server).start();

        json = new GsonBuilder().create();
    }

    public String END_OF_COMMUNICATION(ArrayList<Object> args) {
        System.out.println("End of communication client side.");
        return "";
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
}
