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

        Scanner scanner = new Scanner(System.in);

        Map<String, InetAddress> networkInterfaces = Network.getIPv4Interfaces();

        String interfaceChoice = "";
        while (!networkInterfaces.containsKey(interfaceChoice)) {
            System.out.println("Which interface to use for the multicast ?");
            for (Map.Entry<String, InetAddress> networkInterface : networkInterfaces.entrySet()) {
                System.out.println(networkInterface.getKey() + " - " + networkInterface.getValue());
            }
            System.out.print("> ");
            interfaceChoice = scanner.next();
        }

        Network.INTERFACE_TO_USE = networkInterfaces.get(interfaceChoice);


        String testToJson = Serialize.serialize(Network.INTERFACE_TO_USE);

        InetAddress testFromJson = Serialize.unserialize(testToJson, InetAddress.class);

        System.out.println(testToJson);
        System.out.println(testFromJson);

        GameModeManager.getInstance().getGameModes();


        /*
        dropDatabase();

        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");

        GameMode mode1 = new GameMode("Mode1.mode");
        GameMode mode2 = new GameMode("Mode2.mode");

        BestScore bestScore1 = new BestScore(player1, mode1, 1400);
        BestScore bestScore2 = new BestScore(player1, mode2, 1200);
        BestScore bestScore3 = new BestScore(player2, mode1, 1300);

        DatabaseManager.getInstance().save(player1);
        DatabaseManager.getInstance().save(player2);

        DatabaseManager.getInstance().save(mode1);
        DatabaseManager.getInstance().save(mode2);

        DatabaseManager.getInstance().save(bestScore1);
        DatabaseManager.getInstance().save(bestScore2);
        DatabaseManager.getInstance().save(bestScore3);

        DatabaseManager.getInstance().close();
        */

        Application.launch(ServerConfiguration.class, args);
    }
}
