package ch.tofind.reflexia.mode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Random;

/**
 * Game Mode class
 */
@XmlType(propOrder = { "name", "startingScore", "endingScore", "rounds" , "gameObjects", "minTimeToSpawn", "maxTimeToSpawn"})
@XmlRootElement(name = "mode")
public class GameMode {

    //! Game Mode name
    private String name;

    //! Score to start with
    private Integer startingScore;

    //! Score to end with
    private Integer endingScore;

    //! Number of rounds
    private Integer rounds;

    //! Game objects composed by the mode
    private GameObjects gameObjects;

    //! Time beetween every object spawn
    private Integer minTimeToSpawn;

    //! Time beetween every object spawn
    private Integer maxTimeToSpawn;

    //! Interface objects composed by the mode
    private InterfaceObjects interfaceObjects;

    /**
     * GameMode parameterless constructor
     */
    private GameMode() {
        this.gameObjects = new GameObjects();
        this.interfaceObjects = new InterfaceObjects();
    }

    /**
     * Create a game mode
     *
     * @param name Name of the mode
     * @param startingScore Starting score
     * @param endingScore Ending score
     * @param rounds Number of rounds
     * @param gameObjects Objects composed by the game mode
     */
    public GameMode(String name, Integer startingScore, Integer endingScore, Integer rounds, GameObjects gameObjects, Integer minTimeToSpawn, Integer maxTimeToSpawn) {
        super();
        this.name = name;
        this.startingScore = startingScore;
        this.endingScore = endingScore;
        this.rounds = rounds;
        this.gameObjects = gameObjects;
        this.minTimeToSpawn = minTimeToSpawn;
        this.maxTimeToSpawn = maxTimeToSpawn;

        this.interfaceObjects = new InterfaceObjects();
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
     * gets the number of rounds of a game mode
     * @return the number of rounds
     */
    @XmlElement(name = "rounds")
    public Integer getRounds() {
        return rounds;
    }

    /**
     * sets the rounds of a game mode
     * @param rounds of a game mode
     */
    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }

    /**
     * gets the gameObjects attributes of game mode
     * @return the objects composed by the game mode
     */
    @XmlElement(name = "gameObjects")
    public GameObjects getGameObjects() {
        return gameObjects;
    }

    /**
     * sets the gameObjects attributes of game mode
     * @param gameObjects
     */
    public void setGameObjects(GameObjects gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * adds a gameObject to the gameObjects of a game mode
     * @param gameObject
     */
    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
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
     * gets the gameObjects attributes of game mode
     * @return the objects composed by the game mode
     */
    public InterfaceObjects getInterfaceObjects() {
        return interfaceObjects;
    }

    /**
     * adds a gameObject to the gameObjects of a game mode
     * @param interfaceObject
     */
    public void addInterfaceObject(InterfaceObject interfaceObject) {
        interfaceObjects.add(interfaceObject);
    }

    public GameObject getRandomGameObject() {

        Random random = new Random();

        GameObjects availableGameObjects = new GameObjects();

        for (GameObject gameObject : gameObjects.getGameObjects()) {

            if (gameObject.getEnabled()) {
                availableGameObjects.add(gameObject);
            }
        }

        Integer randomGameObject = random.nextInt(availableGameObjects.getGameObjects().size());

        return availableGameObjects.getGameObjects().get(randomGameObject);
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
               "Rounds..........: " + rounds        + '\n' +
               gameObjects                          + '\n';
    }
}
