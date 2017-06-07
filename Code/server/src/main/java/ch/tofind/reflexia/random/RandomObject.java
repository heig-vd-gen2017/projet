package ch.tofind.reflexia.random;

import ch.tofind.reflexia.random.Point;

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

    /**
     * Initialisation constructor.
     *
     * @param type    type of the game object
     * @param point   position point of the game object
     */
    public RandomObject(Integer id, String type, Point point) {
        this.id      = id;
        this.type    = type;
        this.point   = point;
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
}
