package ch.tofind.reflexia.mode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "name", "startingScore", "endingScore", "rounds" , "gameObjects"})
@XmlRootElement(name = "mode")
public class GameMode {

    //! Game mode name
    private String name;

    //! BestScore to start with
    private Integer startingScore;

    //! BestScore to end with
    private Integer endingScore;

    //! Number of rounds
    private Integer rounds;

    //! Objects composed by the mode
    private GameObjects gameObjects;

    protected GameMode() {

    }

    /**
     * @brief Create a game mode
     * @param name Name of the mode
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
     * @brief  gets the name of the game mode
     * @return name
     */
    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    /**
     * @brief sets the name of the game mode
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @brief gets the starting score
     * @return starting score
     */
    @XmlElement(name = "startingScore")
    public Integer getStartingScore() {
        return startingScore;
    }

    /**
     * @brief sets the starting score
     * @param startingScore
     */
    public void setStartingScore(Integer startingScore) {
        this.startingScore = startingScore;
    }

    /**
     * @brief gets the ending score
     * @return ending score
     */
    @XmlElement(name = "endingScore")
    public Integer getEndingScore() {
        return endingScore;
    }
    public void setEndingScore(Integer endingScore) {
        this.endingScore = endingScore;
    }

    /**
     * @brief gets the round number
     * @return round
     */
    @XmlElement(name = "rounds")
    public Integer getRounds() {
        return rounds;
    }

    /**
     * @brief sets the number of rounds
     * @param rounds
     */
    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }

    /**
     * @brief gets the game object
     * @return game object
     */
    @XmlElement(name = "gameObjects")
    public GameObjects getGameObjects() {
        return gameObjects;
    }

    /**
     * @brief sets the game object
     * @param gameObjects
     */
    public void setGameObjects(GameObjects gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * 
     * @param gameObject
     */
    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

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
