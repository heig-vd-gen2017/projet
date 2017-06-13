package ch.tofind.reflexia.mode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Game Mode class
 */
@XmlType(propOrder = { "name", "startingScore", "endingScore", "gameObjects", "minTimeToSpawn", "maxTimeToSpawn"})
@XmlRootElement(name = "mode")
public class GameMode {

    //! Game Mode name
    private String name;

    //! Score to start with
    private Integer startingScore;

    //! Score to end with
    private Integer endingScore;

    //! Game objects composed by the mode
    private Map<String, GameObject> gameObjects;

    //! Time beetween every object spawn
    private Integer minTimeToSpawn;

    //! Time beetween every object spawn
    private Integer maxTimeToSpawn;

    /**
     * GameMode parameterless constructor
     */
    private GameMode() {
        this.gameObjects = new HashMap<>();
    }

    /**
     * Create a game mode
     *
     * @param name Name of the mode
     * @param startingScore Starting score
     * @param endingScore Ending score
     * @param minTimeToSpawn Minimum time between each spawn
     * @param maxTimeToSpawn Maximum time between each spawn
     */
    public GameMode(String name, Integer startingScore, Integer endingScore, Integer minTimeToSpawn, Integer maxTimeToSpawn) {
        this.name = name;
        this.startingScore = startingScore;
        this.endingScore = endingScore;
        this.minTimeToSpawn = minTimeToSpawn;
        this.maxTimeToSpawn = maxTimeToSpawn;
    }

    /**
     * gets the name of a game mode
     * @return the name of the mode
     */
    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    /**
     * sets the name of a game mode
     * @param name of a game mode
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the starting score of a game mode
     * @return the starting score
     */
    @XmlElement(name = "startingScore")
    public Integer getStartingScore() {
        return startingScore;
    }

    /**
     * sets the starting score of a game mode
     */
    public void setStartingScore(Integer startingScore) {
        this.startingScore = startingScore;
    }

    /**
     * gets the ending score of a game mode
     * @return the ending score
     */
    @XmlElement(name = "endingScore")
    public Integer getEndingScore() {
        return endingScore;
    }

    /**
     * gets the ending score of a game mode
     * @return the ending score
     */
    public void setEndingScore(Integer endingScore) {
        this.endingScore = endingScore;
    }

    /**
     * gets the gameObjects attributes of game mode
     * @return the objects composed by the game mode
     */
    @XmlElement(name = "gameObjects")
    public Map<String, GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * sets the gameObjects attributes of game mode
     * @param gameObjects
     */
    public void setGameObjects(Map<String, GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * Get the min time to spawn
     * @return The min time to spawn
     */
    @XmlElement(name = "minTimeToSpawn")
    public Integer getMinTimeToSpawn() {
        return minTimeToSpawn;
    }

    /**
     * Set the min time to spawn
     * @param minTimeToSpawn The min time to spawn
     */
    public void setMinTimeToSpawn(Integer minTimeToSpawn) {
        this.minTimeToSpawn = minTimeToSpawn;
    }

    /**
     * Get the max time to spawn
     * @return The max time to spawn
     */
    @XmlElement(name = "maxTimeToSpawn")
    public Integer getMaxTimeToSpawn() {
        return maxTimeToSpawn;
    }

    /**
     * Set the max time to spawn
     * @param maxTimeToSpawn The min time to spawn
     */
    public void setMaxTimeToSpawn(Integer maxTimeToSpawn) {
        this.maxTimeToSpawn = maxTimeToSpawn;
    }

    /**
     * toString overloaded method for game mode
     * @return a string representation of the game mode
     */
    @Override
    public String toString() {
        return "GameMode"                           + '\n' + '\t' +
               "Name............: " + name          + '\n' + '\t' +
               "Starting score..: " + startingScore + '\n' + '\t' +
               "Ending  score...: " + endingScore   + '\n' + '\t' +
               gameObjects                          + '\n';
    }
}
