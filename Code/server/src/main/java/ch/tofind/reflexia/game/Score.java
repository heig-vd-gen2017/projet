/*
    https://blog.axopen.com/2013/11/les-cles-primaires-composees-avec-hibernate-4/
    https://vladmihalcea.com/2016/08/01/the-best-way-to-map-a-composite-primary-key-with-jpa-and-hibernate/
*/
package ch.tofind.reflexia.game;

import ch.tofind.reflexia.database.DatabaseObject;

import java.util.Objects;

public class Score implements DatabaseObject {

    private ScoreId id;

    //! Number of votes for the track
    private Integer votes;

    /**
     * Create a link between player and mode
     * @param player
     * @param mode
     */
    public Score(ch.tofind.commusica.media.Player player, ch.tofind.commusica.media.Mode mode) {
        this.id = new PlaylistTrackId(player, mode);
        this.votes = 0;
    }

    public Score() {

    }

    public ch.tofind.commusica.media.Player getPlaylist() {
        return id.getPlaylist();
    }

    public ch.tofind.commusica.media.Mode getTrack() {
        return id.getTrack();
    }

    public Integer getVotes() {
        return votes;
    }

    public void upvote() {
        votes++;
    }

    public void downvote() {
        votes--;
    }

    @Override
    public boolean equals(Object object) {

        if (object == this) {
            return true;
        }

        if (!(object instanceof ch.tofind.commusica.playlist.Score)) {
            return false;
        }

        ch.tofind.commusica.playlist.Score score = (ch.tofind.commusica.playlist.Score) object;

        return Objects.equals(id, score.id);
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

        return "Score"                           + '\n' + '\t' +
               "Player: " + id.getPlaylist().getName() + '\n' + '\t' +
               "Mode...: " + id.getTrack().getTitle()   + '\n' + '\t' +
               "Votes...: " + votes                      + '\n';
    }
}
