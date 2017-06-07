package ch.tofind.reflexia.random;

/**
 * This enumeration represents the type of game object of the application.
 *
 * @author Luca Sivillica
 */
public enum TypeObject {
    BONUS,
    MALUS,
    MYSTERY;

    public String toString() {
        return name().toLowerCase().toString();
    }
}
