/*
    https://blog.axopen.com/2013/11/les-cles-primaires-composees-avec-hibernate-4/
    https://vladmihalcea.com/2016/08/01/the-best-way-to-map-a-composite-primary-key-with-jpa-and-hibernate/
*/
package ch.tofind.reflexia.game;

import ch.tofind.reflexia.database.DatabaseObject;

import java.util.Date;
import java.util.Objects;

public class BestScore implements DatabaseObject {

    private BestScoreId id;

    //! Highest score achieved in the defined game mode
    private Integer bestScore;

    //! When the highest score was achieved
    private Date date;

    /**
     * Create a link between player and mode
     * @param player
     * @param mode
     */
    public BestScore(Player player, Mode mode, Integer bestScore) {
        this.id = new BestScoreId(player, mode);
        this.bestScore = bestScore;
        this.date = new Date();
    }

    public BestScore() {

    }

    public Player getPlayer() {
        return id.getPlayer();
    }

    public Mode getMode() {
        return id.getMode();
    }

    public Integer getBestScore() {
        return bestScore;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object object) {

        if (object == this) {
            return true;
        }

        if (!(object instanceof BestScore)) {
            return false;
        }

        BestScore bestScore = (BestScore) object;

        return Objects.equals(id, bestScore.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public void update() {

    }

    @Override
    public String toString() {

        return "BestScore"                                 + '\n' + '\t' +
               "Player....: " + id.getPlayer().getPseudo() + '\n' + '\t' +
               "GameMode......: " + id.getMode().getName()     + '\n' + '\t' +
               "Best score: " + bestScore                  + '\n';
    }
}
