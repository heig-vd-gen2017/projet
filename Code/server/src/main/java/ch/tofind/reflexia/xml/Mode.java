package ch.tofind.reflexia.xml;

import ch.tofind.reflexia.game.GameObjects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "name", "startScore", "endScore", "rounds" , "gameObjects"})
@XmlRootElement(name = "mode")
public class Mode {

    //!
    private String name;

    //!
    private Integer startScore;

    //!
    private Integer endScore;

    //!
    private Integer rounds;

    //!
    private GameObjects gameObjects;

    public Mode() {

    }

    public Mode(String name, Integer startScore, Integer endScore, Integer rounds, GameObjects gameObjects) {
        super();
        this.name = name;
        this.startScore = startScore;
        this.endScore = endScore;
        this.rounds = rounds;
        this.gameObjects = gameObjects;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "startScore")
    public Integer getStartScore() {
        return startScore;
    }
    public void setStartScore(Integer startScore) {
        this.startScore = startScore;
    }

    @XmlElement(name = "endScore")
    public Integer getEndScore() {
        return endScore;
    }
    public void setEndScore(Integer endScore) {
        this.endScore = endScore;
    }

    @XmlElement(name = "rounds")
    public Integer getRounds() {
        return rounds;
    }
    public void setRounds(Integer rounds) {
        this.rounds = rounds;
    }

    @XmlElement(name = "gameObjects")
    public GameObjects getGameObjects() {
        return gameObjects;
    }
    public void setGameObjects(GameObjects gameObjects) {
        this.gameObjects = gameObjects;
    }

    @Override
    public String toString() {
        return "Mode"                          + '\n' + '\t' +
               "Name.........: " + name        + '\n' + '\t' +
               "Start score..: " + startScore  + '\n' + '\t' +
               "End score....: " + endScore    + '\n' + '\t' +
               "Rounds.......: " + rounds      + '\n' +
               gameObjects + '\n';
    }
}
