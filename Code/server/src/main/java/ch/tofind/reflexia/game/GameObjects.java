package ch.tofind.reflexia.game;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "gameObjects"})
@XmlRootElement(name = "gameObjects")
public class GameObjects {

    //! Objects for the mode
    private List<GameObject> gameObjects;
    
    GameObjects() {

    }
    
    GameObjects(List<GameObject> gameObjects){
        super();
        this.gameObjects = gameObjects;
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
