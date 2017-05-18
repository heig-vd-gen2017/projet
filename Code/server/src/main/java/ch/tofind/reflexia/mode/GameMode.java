package ch.tofind.reflexia.mode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @brief Game Mode class
 */
@XmlType(propOrder = { "name", "startingScore", "endingScore", "rounds" , "gameObjects"})
@XmlRootElement(name = "mode")
public class GameMode {

    //! Game Mode name
    private String name;

    //! BestScore to start with
    private Integer startingScore;

    //! BestScore to end with
    private Integer endingScore;

    //! Number of rounds
    private Integer rounds;

    //! Objects composed by the mode
    private GameObjects gameObjects;

     //! @brief GameMode parameterless constructor
    protected GameMode() {

    }

    /**
     * @brief Game Mode constructor with parameters
     * @param name name of the mode
     * @param startingScore Starting score
     * @param endingScore Ending score
     * @param rounds Number of rounds
     * @param gameObjects Objects composed by the game mode
     */
    public GameMode(String name, Integer startingScore, Integer endingScore, Integer rounds, GameObjects gameObjects) {
        super();
        this.name = name;
        this.startingScore = startingScore;
        this.endingScore = endingScore;
        this.rounds = rounds;
        this.gameObjects = gameObjects;
    }

    /**
     * @brief gets the name of a game mode
     * @return the name of the mode
     */
    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    /**
     * @brief sets the name of a game mode
     * @param name of a game mode
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @brief gets the starting score of a game mode
     * @return the starting score
     */
    @XmlElement(name = "startingScore")
    public Integer getStartingScore() {
        return startingScore;
    }

    /**
     * @brief sets the starting score of a game mode
     */
    public void setStartingScore(Integer startingScore) {
        this.startingScore = startingScore;
    }

    /**
     * @brief gets the ending score of a game mode
     * @return the ending score
     */
    @XmlElement(name = "endingScore")
    public Integer getEndingScore() {
        return endingScore;
    }

    /**
     * @brief gets the ending score of a game mode
     * @return the ending score
     */
    public void setEndingScore(Integer endingScore) {
        this.endingScore = endingScore;
    }

    /**
     * @brief gets the number of rounds of a game mode
     * @return the number of rounds
     */
    @XmlElement(name = "rounds")
    public Integer getRounds() {
        return rounds;
    }

    /**
     * @brief sets the rounds of a game mode
     * @param rounds of a game mode
     */
    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }

    /**
     * @brief gets the gameObjects attributes of game mode
     * @return the objects composed by the game mode
     */
    @XmlElement(name = "gameObjects")
    public GameObjects getGameObjects() {
        return gameObjects;
    }

    /**
     * @brief sets the gameObjects attributes of game mode
     * @param gameObjects
     */
    public void setGameObjects(GameObjects gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * @brief adds a gameObject to the gameObjects of a game mode
     * @param gameObject
     */
    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    /**
     * @brief toString overloaded method for game mode
     * @return a string representation of the game mode
     */
    @Override
    public String toString() {
        return "GameMode"                            + '\n' + '\t' +
               "Name.........: " + name          + '\n' + '\t' +
               "Start score..: " + startingScore + '\n' + '\t' +
               "End score....: " + endingScore   + '\n' + '\t' +
               "Rounds.......: " + rounds        + '\n' +
               gameObjects                       + '\n';
    }
}
