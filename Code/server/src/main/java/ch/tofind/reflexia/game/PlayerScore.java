package ch.tofind.reflexia.game;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * This class represents an audio track.
 */
public class PlayerScore implements Serializable {

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
    protected PlayerScore() {

    }

    /**
     * Create a score.
     *
     * @param player The player.
     * @param mode The mode.
     * @param score The score.
     */
    public PlayerScore(String player, String mode, Integer score) {
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

        if (!(object instanceof PlayerScore)) {
            return false;
        }

        PlayerScore playerScore = (PlayerScore) object;

        return Objects.equals(id, playerScore.id)          &&
                Objects.equals(player, playerScore.player) &&
                Objects.equals(mode, playerScore.mode)     &&
                Objects.equals(playerScore, playerScore.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, mode, score);
    }

    @Override
    public String toString() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.YYYY HH:mm");

        String dateAddedString = date == null ? "N/A" : dateFormat.format(date);

        return "PlayerScore"                  + '\n' + '\t' +
               "Player.....: " + player + '\n' + '\t' +
               "Mode.......: " + mode   + '\n' + '\t' +
               "PlayerScore......: " + score  + '\n' + '\t' +
               "Date.......: " + dateAddedString   + '\n';
    }
}
