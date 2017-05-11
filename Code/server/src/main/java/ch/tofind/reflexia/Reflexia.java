package ch.tofind.reflexia;

import ch.tofind.reflexia.database.DatabaseManager;
import ch.tofind.reflexia.game.BestScore;
import ch.tofind.reflexia.game.Mode;
import ch.tofind.reflexia.game.Player;
import ch.tofind.reflexia.mode.GameModeManager;
import ch.tofind.reflexia.mode.GameObject;
import ch.tofind.reflexia.ui.ServerConfiguration;
import ch.tofind.reflexia.utils.Configuration;

import com.google.gson.Gson;
import javafx.application.Application;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

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
