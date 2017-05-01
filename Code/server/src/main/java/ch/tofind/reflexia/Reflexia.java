package ch.tofind.reflexia;

import ch.tofind.reflexia.server.Server;
import ch.tofind.reflexia.utils.Configuration;

import java.io.File;
import java.io.IOException;

public class Reflexia {

    public static void dropDatabase() {
        String filePath = "reflexia.db";
        File dbFile = new File(filePath);

        if (dbFile.exists()) {
            dbFile.delete();
        }
    }

    public static void main(String[] args) throws IOException {

        try {
            Configuration.getInstance().load("reflexia.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Server server = new Server();
        server.handleConnection();
    }
}
