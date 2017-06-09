package ch.tofind.reflexia.mode;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * game object class definition
 */
@XmlType(propOrder = {"type", "enabled", "points", "timeout"})
@XmlRootElement(name = "gameObject")
public class GameObject {

    //! Type of the object
    private String type;

    //! Tells if the object is enabled or not
    private Boolean enabled;

    //! Points that give the object on click
    private Integer points;

    //! Time to show on the surface
    private Integer timeout;

    /**
     * Private empty constructor
     */
    private GameObject() {

    }

    /**
     * Create a game object
     *
     * @param type Type of the object
     * @param enabled Enabled or disabled
     * @param points Points of the game object
     * @param timeout Time to show on surface
     */
    public GameObject(String type, Boolean enabled, Integer points, Integer timeout) {
        this.type = type;
        this.enabled = enabled;
        this.points = points;
        this.timeout = timeout;
    }

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

        return "Game object"                   + '\n' + '\t' +
                "Type............: " + type    + '\n' + '\t' +
                "Enabled.........: " + enabled + '\n' + '\t' +
                "Points..........: " + points  + '\n' + '\t' +
                "Timeout.........: " + timeout + '\n';
    }
}