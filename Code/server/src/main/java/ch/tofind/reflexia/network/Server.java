package ch.tofind.reflexia.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by luca on 26.04.17.
 */
public class Server implements Protocol {

    private ServerSocket serverSocket;

    public Server() throws IOException {
        serverSocket = new ServerSocket(DEFAULT_PORT);
    }

    public void handleConnection() throws IOException {
        Socket gamerSocket;

        while (true) {
            gamerSocket = serverSocket.accept();

            new Thread(new GameHandler(gamerSocket)).start();
        }
    }
}
