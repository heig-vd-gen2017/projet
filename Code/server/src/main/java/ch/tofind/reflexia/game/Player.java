package ch.tofind.reflexia.game;

/**
 * @brief This class represents a player
 */
public class Player {

    //! Name of the player
    private String pseudo;

    private Integer score;

    /**
     * @brief Create a player
     * @param pseudo Name of the player
     * @param initialScore Initial score for the Player
     */
    public Player(String pseudo, Integer initialScore) {
        this.pseudo = pseudo;
        this.score = initialScore;
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
    public Integer getScore() {
        return score;
    }

    /**
     * @brief toString method for player class
     * @return a string representation of a player
     */
    @Override
    public String toString() {

        return "Player"                      + '\n' + '\t' +
               "Pseudo..........: " + pseudo + '\n' + '\t' +
               "Score...........: " + score  + '\n';
    }
}
