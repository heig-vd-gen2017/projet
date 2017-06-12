package ch.tofind.reflexia.game;

import ch.tofind.reflexia.errors.LobbyClosed;
import ch.tofind.reflexia.errors.UsernameTaken;
import ch.tofind.reflexia.mode.GameMode;
import ch.tofind.reflexia.ui.ServerConfiguration;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @brief game management class
 */
public class GameManager {

    //! Shared instance of the object for all the application
    private static GameManager instance = null;

    //! game mode
    private GameMode gameMode;

    //! list of players
    private Map<String, Player> players;

    //! Accept or not new players
    private boolean acceptPlayers;

    //! number of players connected
    private IntegerProperty nbPlayers = new SimpleIntegerProperty(0);

    /**
     * @brief GameManager single constructor. Avoid the instantiation.
     */
    private GameManager() {
        this.players = new HashMap<>();
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

    public void addPlayer(String pseudo) throws LobbyClosed, UsernameTaken {

        if (!acceptPlayers) {
            throw new LobbyClosed("No more players can be accepted");
        }

        Player player = new Player(pseudo, gameMode.getStartingScore());

        if (players.containsKey(player)) {
            throw new UsernameTaken("Username already taken");
        }

        players.put(player.getPseudo(), player);

        nbPlayers.setValue(players.size());
    }

    public Map<String, Player> getPlayers() {
        return players;
    }

    public boolean isWinner(String playerPseudo) {

        Player player = players.get(playerPseudo);

        Integer actualScore = player.getScore();

        return actualScore >= gameMode.getEndingScore();
    }

    public IntegerProperty getNumberOfPlayers() {
        return nbPlayers;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;

    }
}
