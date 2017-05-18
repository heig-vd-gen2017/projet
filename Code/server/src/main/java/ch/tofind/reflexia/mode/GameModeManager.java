package ch.tofind.reflexia.mode;


import ch.tofind.reflexia.utils.Configuration;
import org.apache.commons.io.FileUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @brief GameModeManager class definition
 */
public class GameModeManager {

    //! Where are stored the modes
    private static String MODES_PATH = Configuration.getInstance().get("MODES_PATH");

    //! Shared instance of the object for all the application
    private static GameModeManager instance = null;

    //! All the available game modes
    private Map<String, GameMode> gameModes;

    /**
     * @brief GameModeManager single constructor. Avoid the instantiation.
     */
    private GameModeManager() {

        // Get the game modes path from configuration file
        File gameModesPath = new File(MODES_PATH);

        // Create the modes path if not exists
        gameModesPath.mkdirs();

        // Get all the modes stored in the modes path
        String[] extensions = new String[] { "xml" };
        List<File> gameModesFiles = (List<File>) FileUtils.listFiles(gameModesPath, extensions, true);

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(GameMode.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            gameModes = new HashMap<>();

            for (File gameModeFile : gameModesFiles) {

                GameMode gameGameMode = (GameMode) jaxbUnmarshaller.unmarshal(gameModeFile);

                gameGameMode.addGameObject(new GameObject("mystery", null, null));
                gameGameMode.addGameObject(new GameObject("waiting", null, null));
                gameGameMode.addGameObject(new GameObject("starting_in_3", null, 2000));
                gameGameMode.addGameObject(new GameObject("starting_in_2", null, 2000));
                gameGameMode.addGameObject(new GameObject("starting_in_1", null, 2000));
                gameGameMode.addGameObject(new GameObject("start", null, 2000));
                gameGameMode.addGameObject(new GameObject("winner", null, null));
                gameGameMode.addGameObject(new GameObject("loser", null, null));
                gameGameMode.addGameObject(new GameObject("end_of_game", null, null));
                gameGameMode.addGameObject(new GameObject("restart", null, null));
                gameGameMode.addGameObject(new GameObject("background", null, null));

                gameModes.put(gameGameMode.getName(), gameGameMode);

                System.out.println(gameGameMode);

            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Get the object instance
     * @return The instance of the object
     */
    public static GameModeManager getInstance() {

        if(instance == null) {
            synchronized (GameModeManager.class) {
                if (instance == null) {
                    instance = new GameModeManager();
                }
            }
        }

        return instance;
    }

    /**
     * @brief gets the gameModes attributeof GameModes
     * @return gameModes attribute
     */
    public Map<String, GameMode> getGameModes() {

        return gameModes;
    }
}
