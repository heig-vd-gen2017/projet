package ch.tofind.reflexia.mode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


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


    @XmlElement(name = "name")
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    @XmlElement(name = "startingScore")
    public Integer getStartingScore() {
        return startingScore;
    }


    public void setStartingScore(Integer startingScore) {
        this.startingScore = startingScore;
    }


    @XmlElement(name = "endingScore")
    public Integer getEndingScore() {
        return endingScore;
    }


    public void setEndingScore(Integer endingScore) {
        this.endingScore = endingScore;
    }


    @XmlElement(name = "gameObjects")
    public Map<String, GameObject> getGameObjects() {
        return gameObjects;
    }


    public void setGameObjects(Map<String, GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }


    @XmlElement(name = "minTimeToSpawn")
    public Integer getMinTimeToSpawn() {
        return minTimeToSpawn;
    }


    public void setMinTimeToSpawn(Integer minTimeToSpawn) {
        this.minTimeToSpawn = minTimeToSpawn;
    }


    @XmlElement(name = "maxTimeToSpawn")
    public Integer getMaxTimeToSpawn() {
        return maxTimeToSpawn;
    }


    public void setMaxTimeToSpawn(Integer maxTimeToSpawn) {
        this.maxTimeToSpawn = maxTimeToSpawn;
    }


    @Override
    public String toString() {
        return "GameMode"                           + '\n' + '\t' +
               "Name............: " + name          + '\n' + '\t' +
               "Starting score..: " + startingScore + '\n' + '\t' +
               "Ending  score...: " + endingScore   + '\n' + '\t' +
               gameObjects                          + '\n';
    }
}
