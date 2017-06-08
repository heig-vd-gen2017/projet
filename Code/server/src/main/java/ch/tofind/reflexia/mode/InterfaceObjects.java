package ch.tofind.reflexia.mode;

import java.util.ArrayList;
import java.util.List;

/**
 * game objects class , a list of game object
 */
public class InterfaceObjects {

    //! Objects for the mode
    private List<InterfaceObject> interfaceObjects;

    //! game objects parameterless constructor
    public InterfaceObjects() {
        interfaceObjects = new ArrayList<>();
    }

    /**
     * Add an interface object.
     * @param interfaceObject The interface object to add.
     */
    public void add(InterfaceObject interfaceObject) {
        interfaceObjects.add(interfaceObject);
    }

    /**
     * Get the list of the interface objects.
     * @return The interface objects.
     */
    public List<InterfaceObject> getInterfacesObjects() {
        return interfaceObjects;
    }

    /**
     * toString method for a list of game objects
     * @return a string representation of a list of game objects
     */
    @Override
    public String toString() {

        String result = "";

        for (InterfaceObject interfaceObject : interfaceObjects) {
            result += interfaceObject;
        }

        return result;
    }
}
