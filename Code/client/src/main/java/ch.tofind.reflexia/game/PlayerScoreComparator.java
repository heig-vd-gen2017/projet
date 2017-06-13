package ch.tofind.reflexia.game;

import java.util.Comparator;

public class PlayerScoreComparator implements Comparator<Player> {

    @Override
    public int compare(Player x, Player y) {
        return x.getScore() + y.getScore();
    }
}