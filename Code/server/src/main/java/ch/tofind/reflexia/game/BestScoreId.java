package ch.tofind.reflexia.game;

import java.io.Serializable;
import java.util.Objects;

public class BestScoreId implements Serializable {

    //! Player related to the mode
    private Player player;

    //! GameMode related to the player
    private Mode mode;

    /**
     * Create a link between player and mode
     */
    public BestScoreId() {

    }

    /**
     * Create a link between player and mode
     * @param player
     * @param mode
     */
    public BestScoreId(Player player, Mode mode) {
        this.player = player;
        this.mode = mode;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }

    @Override
    public boolean equals(Object object) {

        if (object == this) {
            return true;
        }

        if (!(object instanceof BestScoreId)) {
            return false;
        }

        BestScoreId bestScoreId = (BestScoreId) object;


        return Objects.equals(player, bestScoreId.player) &&
                Objects.equals(mode, bestScoreId.mode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, mode);
    }
}
