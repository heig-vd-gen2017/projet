package ch.tofind.reflexia.game;

import ch.tofind.reflexia.mode.GameMode;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

    //! Shared instance of the object for all the application
    private static GameManager instance = null;

    //!
    private GameMode gameMode;

    //!
    private List<Player> players;

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

    public void addPlayer(Player player) {
        players.add(player);
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;

    }
}
