package ch.tofind.reflexia.random;

import ch.tofind.reflexia.core.ApplicationProtocol;
import ch.tofind.reflexia.network.MulticastClient;
import ch.tofind.reflexia.network.NetworkProtocol;
import ch.tofind.reflexia.utils.Network;
import ch.tofind.reflexia.utils.Serialize;

import java.util.Random;

/**
 * This class represents an object randomizer that allows to generate random game objects to send to the client.
 */
public class ObjectRandomizer implements Runnable {

    //! size of the sprite
    public final static Integer SIZE_OBJECT  = 64;

    //! minimum timeout
    public final static Integer MIN_TIME_OUT = 1000;

    //! maximum timeout
    public final static Integer MAX_TIME_OUT = 3000;

    //! minimum position of the coordinate of x
    public final static Integer MIN_POS_X = -20 + SIZE_OBJECT / 2;

    //! maximum position of the coordinate of x
    public final static Integer MAX_POS_X = 250 - SIZE_OBJECT / 2;

    //! minimum position of the coordinate of y
    public final static Integer MIN_POS_Y = 0 + SIZE_OBJECT / 2;

    //! maximum position of the coordinate of y
    public final static Integer MAX_POS_Y = 230 - SIZE_OBJECT / 2;

    //! Multicast where to send the random object
    private MulticastClient multicastClient;

    //! random generator
    private Random random;

    //! Tells if the randomizer is running or not
    private boolean running;

    /**
     * Initialisation constructor.
     */
    public ObjectRandomizer(MulticastClient multicastClient) {
        this.multicastClient = multicastClient;
        this.random = new Random();
        this.running = false;
    }

    /**
     * Allows to get an random game object.
     *
     * @return the corresponding random game object
     */
    public RandomObject getRandomObject() {

        Integer id = random.nextInt(Integer.MAX_VALUE);

        Integer randomIndexType = random.nextInt(TypeObject.values().length);

        String type = TypeObject.values()[randomIndexType].toString();

        Point point = Point.getRandonPointBetween(MIN_POS_X, MAX_POS_X, MIN_POS_Y, MAX_POS_Y);

        return new RandomObject(id, type, point);
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {

        running = true;

        while (running) {

            RandomObject randomObject = getRandomObject();
            String randomObjectJson = Serialize.serialize(randomObject);

            Integer timeout = random.nextInt(MAX_TIME_OUT - MIN_TIME_OUT + 1) + MIN_TIME_OUT;

            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            multicastClient.send(ApplicationProtocol.SEND_OBJECT + NetworkProtocol.END_OF_LINE +
            randomObjectJson + NetworkProtocol.END_OF_LINE +
            NetworkProtocol.END_OF_COMMAND);
        }
    }
}
