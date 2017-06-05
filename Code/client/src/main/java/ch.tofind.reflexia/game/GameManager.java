package ch.tofind.reflexia.game;

import ch.tofind.reflexia.errors.LobbyClosed;
import ch.tofind.reflexia.errors.UsernameTaken;
import ch.tofind.reflexia.mode.GameMode;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @brief game management class
 */
public class GameManager {

    //! Shared instance of the object for all the application
    private static GameManager instance = null;

    //! game mode
    private GameMode gameMode;

    //! list of players
    private List<Player> players;

    //! Accept or not new players
    private boolean acceptPlayers;

    //! number of players connected
    private IntegerProperty nbPlayers = new SimpleIntegerProperty(0);

    /**
     * @brief GameManager single constructor. Avoid the instantiation.
     */
    private GameManager() {
        this.players = new ArrayList<>();
        this.acceptPlayers = false;
    }

    /**
     * @brief Get the object instance
     * @return The instance of the object
     */
    public static GameManager getInstance() {

        if(instance == null) {
            synchronized (GameManager.class) {
                if (instance == null) {
                    instance = new GameManager();
                }
            }
        }

        return instance;
    }

    public void acceptPlayers() {
        acceptPlayers = true;
    }

    public void refusePlayers() {
        acceptPlayers = false;
    }

    public void reset() {
        players.clear();
    }

    /**
     * @brief adds a player
     * @param player
     */
    public void addPlayer(String pseudo) throws LobbyClosed, UsernameTaken {

        if (!acceptPlayers) {
            throw new LobbyClosed("No more players can be accepted");
        }

        Player player = new Player(pseudo, 0);

        if (players.contains(player)) {
            throw new UsernameTaken("Username already taken");
        }

        players.add(player);

        nbPlayers.setValue(players.size());
    }

    /**
     * gets the number of players
     * @return the number of players
     */
    public IntegerProperty getNumberOfPlayers() {
        return nbPlayers;
    }

    /**
     * @brief gets the game mode
     */
    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     * @brief sets the game mode
     * @param gameMode
     */
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;

    }
}
