package ch.tofind.reflexia.game;

import java.util.List;

/**
 * Created by Denise on 01.05.2017.
 */
public class Mode {

    private String name;
    private int start_score;
    private int end_score;
    private int rounds;
    private List<GameObject> gameObjects;

    public Mode(String name, int start_score, int end_score, int rounds, List<GameObject> gameObjects){
        this.name = name;
        this.start_score = start_score;
        this.end_score = end_score;
        this.rounds = rounds;
        this.gameObjects = gameObjects;
    }

}
