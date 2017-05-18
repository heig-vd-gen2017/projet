package ch.tofind.reflexia;

import ch.tofind.reflexia.mode.GameModeManager;
import ch.tofind.reflexia.ui.ServerConfiguration;
import ch.tofind.reflexia.utils.Configuration;

import ch.tofind.reflexia.utils.Network;
import ch.tofind.reflexia.utils.Serialize;
import javafx.application.Application;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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

        Application.launch(ServerConfiguration.class, args);
    }
}
