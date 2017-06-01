package ch.tofind.reflexia.utils;

/**
 * This class represents an random game object to send to the client.
 *
 * @author Luca Sivillica
 */
public class RandomObject {

    //! unique id of the game object
    private Integer id;

    //! game object type
    private String type;

    //! current point of the game object
    private Point point;

    //! game object timeout
    private Integer timeout;

    /**
     * Initialisation constructor.
     *
     * @param type    type of the game object
     * @param point   position point of the game object
     * @param timeout timeout of the game object
     */
    public RandomObject(Integer id, String type, Point point, Integer timeout) {
        this.id      = id;
        this.type    = type;
        this.point   = point;
        this.timeout = timeout;
    }

    /**
     * Getter.
     *
     * @return the unique id of the game object
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter.
     *
     * @return the type of the game object
     */
    public String getType() {
        return type;
    }

    /**
     * Getter.
     *
     * @return the position point of the game object
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Getter.
     *
     * @return the timeout of the game object
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * Setter.
     *
     * @param type the type of the game object
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Setter.
     *
     * @param point the position point of the game object
     */
    public void setPoint(Point point) {
        this.point = point;
    }

    /**
     * Setter.
     *
     * @param timeout the timeout of the game object
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
