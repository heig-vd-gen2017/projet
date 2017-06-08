package ch.tofind.reflexia.mode;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @brief game object class definition
 */
@XmlRootElement(name = "gameObject")
public class GameObject {

    //! game Object type
    private String type;

    //! game Object current points
    private Integer points;

    //! game Object timeout
    private Integer timeout;

    //!
    private Boolean enabled;


    //! game Object constructor
    protected GameObject() {

    }

    /**
     * @brief game object constructor with parameters
     * @param type
     * @param points
     * @param timeout
     */
    public GameObject(String type, Boolean enabled, Integer points, Integer timeout) {
        this.type = type;
        this.enabled = enabled;
        this.points = points;
        this.timeout = timeout;
    }

    /**
     * @brief gets the type of a game object
     * @return the type of a game object
     */
    @XmlAttribute(name = "type")
    public String getType() {
        return type;
    }

    /**
     * @brief sets the type of a game object
     * @param type of a game object
     */
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

    /**
     * @brief gets the game object current points
     * @return current points
     */
    @XmlElement(name = "points")
    public Integer getPoints() {
        return points;
    }

    /**
     * @brief sets game object current points
     * @param points
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * @brief gets game object time out
     * @return game object time out
     */
    @XmlElement(name = "timeout")
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * @brief sets game object timeout
     * @param timeout
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    /**
     * @brief toString function for a game object
     * @return a string representation of a game object
     */
    @Override
    public String toString() {

        return "Game object"                   + '\n' + '\t' +
                "Type............: " + type    + '\n' + '\t' +
                "Points..........: " + points  + '\n' + '\t' +
                "Enabled.........: " + enabled + '\n' + '\t' +
                "Timeout.........: " + timeout + '\n';
    }
}