package ch.tofind.reflexia.game;

import ch.tofind.reflexia.mode.GameMode;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * game management class
 */
public class GameManager {

    //! Shared instance of the object for all the application
    private static GameManager instance = null;

    //! game mode
    private GameMode gameMode;

    //! number of players connected
    private IntegerProperty actualScore;


    /**
     * GameManager single constructor. Avoid the instantiation.
     */
    private GameManager() {
        this.actualScore = new SimpleIntegerProperty(0);
    }

    /**
     * Get the object instance
     * @return The instance of the object
     */
    public static GameManager getInstance() {

        if(instance == null) {
            synchronized (GameManager.class) {
                if (instance == null) {
                    instance = new GameManager();
                }
            }
        }

        return instance;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;

    }

    public IntegerProperty getActualScore() {
        return actualScore;
    }

    public void setActualScore(Integer score) {
        actualScore.set(score);
    }
}
