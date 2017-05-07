package ch.tofind.reflexia.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denise on 06.05.2017.
 */
public class GameManager {

    private String ipAddress;
    private int port;
    private int nbOfGamers;
    private Mode mode;
    private List<Player> players;

    public GameManager(String ipAddress, int port, Mode mode) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.mode = mode;
        nbOfGamers = 0; // or 1 if the admin is taken as a player directly
        this.players = new ArrayList<Player>();
    }

    public synchronized void addPlayer(Player p) {
        players.add(p);
        ++nbOfGamers;
    }
}
