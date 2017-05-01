package ch.tofind.reflexia.game;

import java.io.Serializable;
import java.util.Objects;

public class ScoreId implements Serializable {

    //! Player related to the mode
    private ch.tofind.commusica.media.Player player;

    //! Mode related to the player
    private ch.tofind.commusica.media.Mode mode;

    /**
     * Create a link between player and mode
     */
    public ScoreId() {

    }

    /**
     * Create a link between player and mode
     * @param player
     * @param mode
     */
    public ScoreId(ch.tofind.commusica.media.Player player, ch.tofind.commusica.media.Mode mode) {
        this.player = player;
        this.mode = mode;

    }
    public void setPlayer(ch.tofind.commusica.media.Player player) {
        this.player = player;
    }

    public ch.tofind.commusica.media.Player getPlayer() {
        return player;
    }

    public void setMode(ch.tofind.commusica.media.Mode mode) {
        this.mode = mode;
    }

    public ch.tofind.commusica.media.Mode getMode() {
        return mode;
    }

    @Override
    public boolean equals(Object object) {

        if (object == this) {
            return true;
        }

        if (!(object instanceof ch.tofind.commusica.playlist.ScoreId)) {
            return false;
        }

        ch.tofind.commusica.playlist.ScoreId scoreId = (ch.tofind.commusica.playlist.ScoreId) object;


        return Objects.equals(player, scoreId.player) &&
                Objects.equals(mode, scoreId.mode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, mode);
    }
}
