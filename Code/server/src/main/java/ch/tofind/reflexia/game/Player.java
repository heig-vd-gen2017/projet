package ch.tofind.reflexia.game;

import ch.tofind.reflexia.database.DatabaseObject;
import ch.tofind.reflexia.utils.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @brief This class represents a player and can be stored in a database
 */
public class Player implements DatabaseObject {

    //! ID of the player
    private Integer id;

    //! Name of the player
    private String pseudo;

    //! When was the player added for the first time in the database
    private Date dateAdded;

    //! When was the track played for the last time
    private Date datePlayed;

    //! Version control for concurrency
    private Integer version;

    /**
     * @brief Empty constructor for Hibernate
     */
    protected Player() {

    }

    /**
     * @brief Create a player
     * @param pseudo Name of the player
     */
    public Player(String pseudo) {
        this.pseudo = pseudo;
        this.dateAdded = new Date();
    }

    /**
     * @brief Get the player's ID
     * @return The player's ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @brief Get the player's name
     * @return The player's name
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * @brief Get the date when the player was added
     * @return The added date
     */
    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     * @brief Get the date when the player was played
     * @return The played date
     */
    public Date getDatePlayed() {
        return datePlayed;
    }

    @Override
    public void update() {
        this.datePlayed = new Date();
    }

    @Override
    public boolean equals(Object object) {

        if (object == this) {
            return true;
        }

        if (!(object instanceof Player)) {
            return false;
        }

        Player player = (Player) object;

        return Objects.equals(pseudo, player.pseudo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pseudo);
    }

    @Override
    public String toString() {

        String format = Configuration.getInstance().get("DEFAULT_DATE_FORMAT");

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);

        String dateAddedString = dateAdded == null ? "N/A" : dateFormat.format(dateAdded);
        String datePlayedString = datePlayed == null ? "N/A" : dateFormat.format(datePlayed);

        return "Player"                           + '\n' + '\t' +
               "Pseudo.....: " + pseudo           + '\n' + '\t' +
               "Date added.: " + dateAddedString  + '\n' + '\t' +
               "Date played: " + datePlayedString + '\n';
    }
}
