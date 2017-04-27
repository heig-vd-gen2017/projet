package ch.tofind.reflexia;

import ch.tofind.reflexia.server.Server;

import java.io.IOException;

public class Reflexia {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.handleConnection();
    }
}
