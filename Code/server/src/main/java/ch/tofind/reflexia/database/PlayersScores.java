package ch.tofind.reflexia.database;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * This class represents an audio track.
 */
public class PlayersScores implements Serializable {

    //! ID of the record in the database
    private Integer id;

    //! Name of the player.
    private String player;

    //! Mode played by the player.
    private String mode;

    //! Total score the player made.
    private Integer score;

    //! When did the player did its score.
    private Date date;

    //! Version control for concurrency.
    private Integer version;

    /**
     * Empty constructor for Hibernate.
     */
    protected PlayersScores() {

    }

    /**
     * Create a score.
     *
     * @param player The player.
     * @param mode The mode.
     * @param score The score.
     */
    public PlayersScores(String player, String mode, Integer score) {
        this.player = player;
        this.mode = mode;
        this.score = score;
        this.date = new Date();
    }

    @Override
    public boolean equals(Object object) {

        if (object == this) {
            return true;
        }

        if (!(object instanceof PlayersScores)) {
            return false;
        }

        PlayersScores playersScores = (PlayersScores) object;

        return Objects.equals(id, playersScores.id)          &&
                Objects.equals(player, playersScores.player) &&
                Objects.equals(mode, playersScores.mode)     &&
                Objects.equals(playersScores, playersScores.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, mode, score);
    }

    @Override
    public String toString() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.YYYY HH:mm");

        String dateAddedString = date == null ? "N/A" : dateFormat.format(date);

        return "PlayersScores"                  + '\n' + '\t' +
               "Player.....: " + player + '\n' + '\t' +
               "Mode.......: " + mode   + '\n' + '\t' +
               "PlayersScores......: " + score  + '\n' + '\t' +
               "Date.......: " + date   + '\n';
    }
}
