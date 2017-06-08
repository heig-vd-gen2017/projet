package ch.tofind.reflexia.mode;

/**
 * This class represents an interface object.
 */
public class InterfaceObject {

    //! The type of the game object.
    private String type;

    //! Time to show on the interface.
    private Integer timeout;

    /**
     * Avoid the direct instantiation.
     */
    private InterfaceObject() {

    }

    /**
     * Create an interface object.
     *
     * @param type Type of the object.
     * @param timeout Time to show on the interface.
     */
    public InterfaceObject(String type, Integer timeout) {
        super();
        this.type = type;
        this.timeout = timeout;
    }

    /**
     * gets the type of a game object
     * @return the type of a game object
     */
    public String getType() {
        return type;
    }

    /**
     * gets game object time out
     * @return game object time out
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * sets game object timeout
     * @param timeout
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    /**
     * toString function for a game object
     * @return a string representation of a game object
     */
    @Override
    public String toString() {

        return "Interface object"                   + '\n' + '\t' +
                "Type............: " + type    + '\n' + '\t' +
                "Timeout.........: " + timeout + '\n';
    }
}