package ch.tofind.reflexia.game;

import ch.tofind.reflexia.database.DatabaseObject;
import ch.tofind.reflexia.utils.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.xml.bind.annotation.XmlElement;

/**
 * @brief This class represents a game mode and can be stored in a database
 */
public class Mode implements DatabaseObject {

    //! ID of the game mode in the database
    private Integer id;

    //! Game mode name
    private String name;

    //! BestScore to start with
    private Integer startingScore;

    //! BestScore to end with
    private Integer endingScore;

    //! Number of rounds
    private Integer rounds;

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
     * @brief Empty constructor
     */
    public Mode() {

    }

    public Mode(String name, Integer startingScore, Integer endingScore, Integer rounds) {
        super();
        this.name = name;
        this.startingScore = startingScore;
        this.endingScore = endingScore;
        this.rounds = rounds;
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
        this.startingScore = startingScore;
        this.endingScore = endingScore;
        this.rounds = rounds;
        this.gameObjects = gameObjects;
         */
        this.dateAdded = new Date();
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

    @XmlElement(name = "rounds")
    public Integer getRounds() {
        return rounds;
    }
    public void setRounds(Integer rounds) {
        this.rounds = rounds;
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

        return "GameMode"                              + '\n' + '\t' +
                "Name.......: " + name             + '\n' + '\t' +
                "Start score: " + startingScore       + '\n' + '\t' +
                "End score..: " + endingScore         + '\n' + '\t' +
                "Rounds.....: " + rounds           + '\n' + '\t' +
                "URI........: " + uri              + '\n' + '\t' +
                "Date added.: " + dateAddedString  + '\n' + '\t' +
                "Date played: " + datePlayedString + '\n';
    }
}