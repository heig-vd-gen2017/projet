package ch.tofind.reflexia.game;

import ch.tofind.reflexia.mode.GameMode;
import ch.tofind.reflexia.ui.ServerConfiguration;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.net.InetAddress;
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

    //! number of players connected
    private int nbPlayers;

    /**
     * @brief GameManager single constructor. Avoid the instantiation.
     */
    private GameManager() {
        this.players = new ArrayList<>();
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

    /**
     * @brief adds a player
     * @param player
     */
    public void addPlayer(Player player) {
        players.add(player);
        //ServerConfiguration.updateNbPlayers();
    }

    /**
     * gets the number of players
     * @return the number of players
     */
    public int getNumberOfPlayers() {
        return nbPlayers;
    }

    /**
     * @brief sets the game mode
     * @param gameMode
     */
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;

    }
}
