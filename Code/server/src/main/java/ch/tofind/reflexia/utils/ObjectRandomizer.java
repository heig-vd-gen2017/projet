package ch.tofind.reflexia.utils;

import java.util.Random;

/**
 * This class represents an object randomizer that allows to generate random game objects
 * to send to the client.
 *
 * @author Luca Sivillica
 */
public class ObjectRandomizer {

    //! size of the sprite
    public final static Integer SIZE_OBJECT  = 64;

    //! minimum timeout
    public final static Integer MIN_TIME_OUT = 1000;

    //! maximum timeout
    public final static Integer MAX_TIME_OUT = 3000;

    //! minimum position of the coordinate of x
    public final static Integer MIN_POS_X    = -20 + SIZE_OBJECT / 2;

    //! maximum position of the coordinate of x
    public final static Integer MAX_POS_X    = 250 - SIZE_OBJECT / 2;

    //! minimum position of the coordinate of y
    public final static Integer MIN_POS_Y    = 0 + SIZE_OBJECT / 2;

    //! maximum position of the coordinate of y
    public final static Integer MAX_POS_Y    = 230 - SIZE_OBJECT / 2;

    //! random generator
    private Random random;

    /**
     * Initialisation constructor.
     */
    public ObjectRandomizer() {
        random = new Random();
    }

    /**
     * Allows to get an random game object.
     *
     * @return the corresponding random game object
     */
    public RandomObject getRandomObject() {
        Integer randomIndexType = random.nextInt(TypeObject.values().length);

        String type = TypeObject.values()[randomIndexType].toString();

        Point point = Point.getRandonPointBetween(MIN_POS_X, MAX_POS_X, MIN_POS_Y, MAX_POS_Y);

        Integer timeout = random.nextInt(MAX_TIME_OUT - MIN_TIME_OUT + 1) + MIN_TIME_OUT;

        Integer idObject = random.nextInt(Integer.MAX_VALUE);

        return new RandomObject(idObject, type, point, timeout);
    }
}
