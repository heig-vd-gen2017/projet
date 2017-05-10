package ch.tofind.reflexia.game;

import ch.tofind.reflexia.database.DatabaseObject;
import ch.tofind.reflexia.utils.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @brief This class represents a game mode and can be stored in a database
 */
public class Mode implements DatabaseObject {

    //! ID of the game mode in the database
    private Integer id;

    //! Game mode name
    private String name;

    //! BestScore to start with
    private Integer startScore;

    //! BestScore to end with
    private Integer endScore;

    //! Number of rounds
    private Integer rounds;

    //! Objects composed by the mode
    private List<GameObject> gameObjects;

    //! Path to the XML mode file
    private String uri;

    //! When was the mode added for the first time in the database
    private Date dateAdded;

    //! When was the mode used for the last time
    private Date datePlayed;

    //! Version control for concurrency
    private Integer version;

    private boolean bonusObjects;

    private boolean malusObjects;

    private boolean mysteryObjects;

    /**
     * @brief Empty constructor for Hibernate
     */
    public Mode() {

    }

    /**
     * @brief Create a new mode
     * @param uri URI of the file
     */
    public Mode(String uri) {
        this.uri = uri;
        /*
        // Load the data from the XML file
        this.name = name;
        this.startScore = startScore;
        this.endScore = endScore;
        this.rounds = rounds;
        this.gameObjects = gameObjects;
         */
        this.dateAdded = new Date();
    }

    /**
     * @brief Get the mode's name
     * @return The mode's name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @brief Get the mode's starting score
     * @return The mode's starting score
     */
    public Integer getStartScore() {
        return startScore;
    }

    public void setStartScore(Integer startScore) {
        this.startScore = startScore;
    }

    /**
     * @brief Get the mode's ending score
     * @return The mode's ending score
     */
    public Integer getEndingScore() {
        return endScore;
    }

    public void setEndingScore(Integer endScore) {
        this.endScore = endScore;
    }

    /**
     * @brief Get the mode's number of rounds
     * @return The mode's number of rounds
     */
    public Integer getRounds() {
        return rounds;
    }

    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }

    /**
     * @brief Get the mode's game objects
     * @return The mode's game objects
     */
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * @return The added date
     * @brief Get the date when the track was added
     */
    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     * @return The played date
     * @brief Get the date when the track was played
     */
    public Date getDatePlayed() {
        return datePlayed;
    }

    public void setBonusObjects(boolean bonusObjects) {
        this.bonusObjects = bonusObjects;
    }

    public boolean getBonusObjects() { return bonusObjects; }

    public void setMalusObjects(boolean malusObjects) {
        this.malusObjects = malusObjects;
    }

    public boolean getMalusObjects() { return malusObjects; }

    public void setMysteryObjects(boolean mysteryObjects) {
        this.mysteryObjects = mysteryObjects;
    }

    public boolean getMysteryObjects() { return mysteryObjects; }


    @Override
    public void update() {
        this.datePlayed = new Date();
    }

    @Override
    public boolean equals(Object object) {

        if (object == this) {
            return true;
        }

        if (!(object instanceof Mode)) {
            return false;
        }

        Mode mode = (Mode) object;

        return Objects.equals(uri, mode.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri);
    }

    @Override
    public String toString() {

        String format = Configuration.getInstance().get("DEFAULT_DATE_FORMAT");

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        String dateAddedString = dateAdded == null ? "N/A" : dateFormat.format(dateAdded);
        String datePlayedString = datePlayed == null ? "N/A" : dateFormat.format(datePlayed);

        return "Mode"                              + '\n' + '\t' +
                "Name.......: " + name             + '\n' + '\t' +
                "Start score: " + startScore       + '\n' + '\t' +
                "End score..: " + endScore         + '\n' + '\t' +
                "Rounds.....: " + rounds           + '\n' + '\t' +
                "URI........: " + uri              + '\n' + '\t' +
                "Date added.: " + dateAddedString  + '\n' + '\t' +
                "Date played: " + datePlayedString + '\n';
    }
}