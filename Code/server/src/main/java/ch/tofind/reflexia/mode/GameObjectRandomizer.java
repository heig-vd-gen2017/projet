package ch.tofind.reflexia.mode;

import ch.tofind.reflexia.core.ApplicationProtocol;
import ch.tofind.reflexia.network.MulticastClient;
import ch.tofind.reflexia.network.NetworkProtocol;
import ch.tofind.reflexia.utils.Point;
import ch.tofind.reflexia.utils.Serialize;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * This class represents an object randomizer that allows to generate random game objects to send to the client.
 */
public class GameObjectRandomizer implements Runnable {

    //! size of the sprite
    public final static Integer SIZE_OBJECT  = 64;

    //! minimum position of the coordinate of x
    public final static Integer MIN_POS_X = 0;

    //! maximum position of the coordinate of x
    public final static Integer MAX_POS_X = 360 - SIZE_OBJECT;

    //! minimum position of the coordinate of y
    public final static Integer MIN_POS_Y = 0;

    //! maximum position of the coordinate of y
    public final static Integer MAX_POS_Y = 360 - SIZE_OBJECT;

    //! Mode currently used to play
    private GameMode gameMode;

    //! Multicast where to send the random object
    private MulticastClient multicastClient;

    private Set<RandomGameObject> generatedRandomGameObjects;

    //! random generator
    private Random random;

    //! Tells if the randomizer is running or not
    private boolean running;

    /**
     * Initialisation constructor.
     */
    public GameObjectRandomizer(GameMode gameMode, MulticastClient multicastClient) {
        this.gameMode = gameMode;
        this.multicastClient = multicastClient;
        this.random = new Random();
        this.generatedRandomGameObjects = new HashSet<>();
        this.running = false;
    }

    public boolean contains(Integer randomGameObjectId) {
        return generatedRandomGameObjects.contains(randomGameObjectId);
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {

        running = true;

        while (running) {

            RandomGameObject randomGameObject = new RandomGameObject();
            generatedRandomGameObjects.add(randomGameObject);

            Integer timeout = random.nextInt(gameMode.getMaxTimeToSpawn() - gameMode.getMinTimeToSpawn() + 1) + gameMode.getMinTimeToSpawn();

            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (multicastClient != null) {

                String randomGameObjectJson = Serialize.serialize(randomGameObject);

                multicastClient.send(ApplicationProtocol.SHOW_OBJECT + NetworkProtocol.END_OF_LINE +
                        randomGameObjectJson + NetworkProtocol.END_OF_LINE +
                        NetworkProtocol.END_OF_COMMAND);
            }
        }
    }

    private class RandomGameObject {

        private Integer id;

        private Point point;

        private String type;

        RandomGameObject() {
            this.id = random.nextInt(Integer.MAX_VALUE);
            this.type = gameMode.getRandomGameObject().getType();
            this.point = Point.getRandonPointBetween(MIN_POS_X, MAX_POS_X, MIN_POS_Y, MAX_POS_Y);
        }

        Integer getId() {
            return id;
        }

        Point getPoint() {
            return point;
        }

        String getType() {
            return type;
        }
    }
}
