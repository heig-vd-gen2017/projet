package ch.tofind.reflexia.mode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(propOrder = { "gameObjects"})
@XmlRootElement(name = "gameObjects")
public class GameObjects {

    //! Objects for the mode
    private List<GameObject> gameObjects;
    
    protected GameObjects() {

    }
    
    public GameObjects(List<GameObject> gameObjects){
        super();
        this.gameObjects = gameObjects;
    }

    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    @XmlElement(name = "gameObject")
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    @Override
    public String toString() {

        String result = "";

        for (GameObject gameObject : gameObjects) {
            result += gameObject;
        }

        return result;
    }
}
