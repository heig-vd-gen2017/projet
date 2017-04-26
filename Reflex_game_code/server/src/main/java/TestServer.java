import server.Server;

import java.io.IOException;

/**
 * Created by luca on 26.04.17.
 */
public class TestServer {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.handleConnection();
    }
}
