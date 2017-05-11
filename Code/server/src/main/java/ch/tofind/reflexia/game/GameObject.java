package ch.tofind.reflexia.game;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gameObject")
public class GameObject {

    //! Type of the object
    private String type;

    //! Number of points that the object brings
    private Integer points;

    //! Time before the object disappears from the player's screen
    private Integer timeout;

    public GameObject() {

    }

    public GameObject(String type, Integer points, Integer timeout) {
        super();
        this.type = type;
        this.points = points;
        this.timeout = timeout;
    }

    @XmlAttribute(name = "type")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "points")
    public Integer getPoints() {
        return points;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }

    @XmlElement(name = "timeout")
    public Integer getTimeout() {
        return timeout;
    }
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "Game object"          + '\n' + '\t' +
                "Type...: " + type    + '\n' + '\t' +
                "Points.: " + points  + '\n' + '\t' +
                "Timeout: " + timeout + '\n';
    }
}
