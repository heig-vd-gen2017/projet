package ch.tofind.reflexia.mode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * game objects class , a list of game object
 */
@XmlType(propOrder = { "gameObjects"})
@XmlRootElement(name = "gameObjects")
public class GameObjects {

    //! Objects for the mode
    private List<GameObject> gameObjects;

    //! game objects parameterless constructor
    public GameObjects() {
        this.gameObjects = new ArrayList<>();
    }

    /**
     * games objetcs constructor with parameters
     * @param gameObjects list of game object
     */
    public GameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * adds a game object to the game objects parameter
     * @param gameObject is a game object
     */
    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    /**
     * gets the list of game objects
     * @return a list of game objects
     */
    @XmlElement(name = "gameObject")
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * sets the list of game objects
     * @param gameObjects the list of game objects
     */
    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * toString method for a list of game objects
     * @return a string representation of a list of game objects
     */
    @Override
    public String toString() {

        String result = "";

        for (GameObject gameObject : gameObjects) {
            result += gameObject;
        }

        return result;
    }
}
