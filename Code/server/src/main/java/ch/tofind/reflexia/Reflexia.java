package ch.tofind.reflexia;

import ch.tofind.reflexia.database.DatabaseManager;
import ch.tofind.reflexia.game.Score;
import ch.tofind.reflexia.ui.ServerConfiguration;
import ch.tofind.reflexia.utils.Configuration;
import javafx.application.Application;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

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

        dropDatabase();

        Random randomGenerator = new Random();

        for (int i = 0; i < 30; i++) {
            int randomScore = randomGenerator.nextInt(100);
            Score score = new Score(UUID.randomUUID().toString(), UUID.randomUUID().toString(), randomScore);
            DatabaseManager.getInstance().save(score);
        }


        Application.launch(ServerConfiguration.class, args);

        DatabaseManager.getInstance().close();
    }
}
