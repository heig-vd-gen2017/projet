package ch.tofind.reflexia.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gameObject")
public class GameObject {

    //!
    private String type;

    //!
    private Integer points;

    //!
    private Integer timeout;


    public GameObject() {

    }

    public GameObject(Integer points, Integer timeout, String type) {
        super();
        this.points = points;
        this.timeout = timeout;
        this.type = type;
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