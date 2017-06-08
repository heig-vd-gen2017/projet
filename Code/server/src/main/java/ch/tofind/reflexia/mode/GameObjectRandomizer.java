package ch.tofind.reflexia.mode;

import ch.tofind.reflexia.core.ApplicationProtocol;
import ch.tofind.reflexia.network.MulticastClient;
import ch.tofind.reflexia.network.NetworkProtocol;
import ch.tofind.reflexia.utils.Point;
import ch.tofind.reflexia.utils.Serialize;

import java.util.*;

/**
 * This class represents an object randomizer that allows to generate random game objects to send to the client.
 */
public class GameObjectRandomizer implements Runnable {

    //! size of the sprite
    private final static Integer OBJECT_IMAGE_SIZE = 64;

    //! minimum position of the coordinate of x
    private final static Integer MIN_POS_X = 0;

    //! maximum position of the coordinate of x
    private final static Integer MAX_POS_X = 360 - OBJECT_IMAGE_SIZE;

    //! minimum position of the coordinate of y
    private final static Integer MIN_POS_Y = 0;

    //! maximum position of the coordinate of y
    private final static Integer MAX_POS_Y = 360 - OBJECT_IMAGE_SIZE;

    //! Multicast where to send the random object
    private MulticastClient multicastClient;

    private Integer minTimeToSpawn;

    private Integer maxTimeToSpawn;

    private List<String> availableGameObjectTypes;

    private Set<RandomGameObject> generatedRandomGameObjects;

    //! random generator
    private Random random;

    //! Tells if the randomizer is running or not
    private boolean running;

    /**
     * Initialisation constructor.
     */
    public GameObjectRandomizer(GameMode gameMode, MulticastClient multicastClient) {

        this.minTimeToSpawn = gameMode.getMinTimeToSpawn();
        this.maxTimeToSpawn = gameMode.getMaxTimeToSpawn();
        this.multicastClient = multicastClient;
        this.random = new Random();
        this.availableGameObjectTypes = new ArrayList<>();
        this.generatedRandomGameObjects = new HashSet<>();
        this.running = false;

        Map<String, GameObject> gameObjects = gameMode.getGameObjects();

        for (Map.Entry<String, GameObject> entry : gameObjects.entrySet()) {

            String gameObjectType = entry.getKey();
            GameObject gameObject = entry.getValue();

            if (gameObject.getEnabled()) {
                availableGameObjectTypes.add(gameObjectType);
            }
        }
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

            Integer timeout = random.nextInt(maxTimeToSpawn - minTimeToSpawn + 1) + minTimeToSpawn;

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
            this.type = getRandomGameObject();
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

        public String getRandomGameObject() {
            int index = random.nextInt(availableGameObjectTypes.size());
            return availableGameObjectTypes.get(index);
        }
    }
}
