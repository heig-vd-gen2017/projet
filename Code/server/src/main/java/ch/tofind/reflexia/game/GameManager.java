package ch.tofind.reflexia.game;

import ch.tofind.reflexia.mode.GameMode;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private InetAddress ipAddress;
    private int port;
    private GameMode gameMode;
    private List<Player> players;

    public GameManager() {

    }

    public GameManager(InetAddress ipAddress, int port, GameMode gameMode) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.gameMode = gameMode;
        this.players = new ArrayList<>();
    }

    public synchronized void addPlayer(Player player) {
        players.add(player);
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    // TODO
    public void start(){

    }

    // TODO
    public void stop() {

    }
}
