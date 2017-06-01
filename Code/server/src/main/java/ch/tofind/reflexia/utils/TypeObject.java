package ch.tofind.reflexia.utils;

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
        StringBuilder str = new StringBuilder();

        str.append(name().charAt(0)).append(name().substring(1).toLowerCase());

        return str.toString();
    }
}
