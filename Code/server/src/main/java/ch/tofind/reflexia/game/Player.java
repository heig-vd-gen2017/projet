package ch.tofind.reflexia.game;

import java.util.Objects;

/**
 * This class represents a player
 */
public class Player {

    //! Name of the player.
    private String pseudo;

    //! PlayerScore associated with the player.
    private Integer score;

    /**
     * Create a player.
     *
     * @param pseudo Name of the player.
     * @param initialScore Initial score for the Player.
     */
    public Player(String pseudo, Integer initialScore) {
        this.pseudo = pseudo;
        this.score = initialScore;
    }

    /**
     * Get the player's name.
     *
     * @return The player's name.
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Get the score of the player.
     *
     * @return The score.
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Set the player's score.
     */
    public void setScore(Integer score) {
        this.score = score;
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

    /**
     * toString method for player class
     * @return a string representation of a player
     */
    @Override
    public String toString() {

        return "Player"                      + '\n' + '\t' +
               "Pseudo..........: " + pseudo + '\n' + '\t' +
               "PlayerScore...........: " + score  + '\n';
    }
}
