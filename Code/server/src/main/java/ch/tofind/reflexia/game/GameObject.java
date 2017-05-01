package ch.tofind.reflexia.game;

/**
 * Created by luca on 26.04.17.
 */
public class GameObject {
    private String sprite;
    private int points;
    private int timeout;

    public GameObject(String sprite, int points, int timeout) {
        this.sprite = sprite;
        this.points = points;
        this.timeout = timeout;
    }




    public String getExampleSprite() {
        return sprite;
    }
}
