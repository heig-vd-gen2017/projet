package ch.tofind.reflexia;

import ch.tofind.reflexia.database.DatabaseManager;
import ch.tofind.reflexia.game.BestScore;
import ch.tofind.reflexia.game.Mode;
import ch.tofind.reflexia.game.Player;
import ch.tofind.reflexia.ui.ServerConfiguration;
import ch.tofind.reflexia.utils.Configuration;
import javafx.application.Application;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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

        // Get the game modes path from configuration file
        File gameModes = new File(Configuration.getInstance().get("MODES_PATH"));

        // Create the modes path
        gameModes.mkdirs();

        dropDatabase();

        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");

        Mode mode1 = new Mode("Mode1.xml");
        Mode mode2 = new Mode("Mode2.xml");

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

        try {

            File file = new File("/home/ludelafo/Desktop/mode.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(ch.tofind.reflexia.xml.Mode.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ch.tofind.reflexia.xml.Mode mode = (ch.tofind.reflexia.xml.Mode) jaxbUnmarshaller.unmarshal(file);

            System.out.println(mode);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        Application.launch(ServerConfiguration.class, args);
    }
}
