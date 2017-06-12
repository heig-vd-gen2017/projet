package ch.tofind.reflexia.ui;

import ch.tofind.reflexia.core.Core;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import ch.tofind.reflexia.game.GameManager;
import ch.tofind.reflexia.mode.GameMode;
import ch.tofind.reflexia.mode.GameObject;
import ch.tofind.reflexia.utils.Configuration;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ClientGame extends Application implements Initializable {

    private static FXMLLoader loader = new FXMLLoader();

    private static final String FXML_FILE = "ui/ClientGame.fxml";

    private static Core core = Core.getInstance();

    static Stage classStage = new Stage();

    @FXML
    Button buttonClose;

    @FXML
    Pane paneGame;

    @FXML
    Label actualScore;

    @FXML
    Label finalScore;

    @FXML
    Label bonusPoints;

    @FXML
    Pane bonusImagePane;

    @FXML
    Label malusPoints;

    @FXML
    Pane malusImagePane;

    @FXML
    Label mysteryPoints;

    @FXML
    Pane mysteryImagePane;


    /**
     * Returns the controller for the current interface.
     *
     * @return The controller for the current interface.
     */
    public static ClientGame getController() {
        return loader.getController();
    }

    public void start(Stage stage) throws IOException {
        URL fileURL = getClass().getClassLoader().getResource(FXML_FILE);

        if (fileURL == null) {
            throw new NullPointerException("FXML file not found.");
        }

        Parent root;

        try {
            root = loader.load(fileURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);

        stage.setTitle("Reflexia");
        stage.setResizable(false);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    public void closeWindow(MouseEvent event) {
        Stage stage = (Stage)buttonClose.getScene().getWindow();
        stage.close();
    }

    /**
     * Displays a popup on the main window with the given message.
     *
     * @param message The message of the popup.
     */
    public void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    /**
     * Add an object to the surface
     */
    public void addObject(Integer objectId, String imagePath, Integer imagePosX, Integer imagePosY, Integer timeToShow) {

        File imageFile = new File(imagePath);

        String imageUri = imageFile.toURI().toString();
        Image img = new Image(imageUri);
        ImageView gameObject = new ImageView(img);

        gameObject.relocate(imagePosX, imagePosY);


        PauseTransition delay = new PauseTransition(Duration.millis(timeToShow));

        delay.setOnFinished(event -> {
            paneGame.getChildren().remove(gameObject);
        });

        delay.play();

        paneGame.getChildren().add(gameObject);

        gameObject.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            paneGame.getChildren().remove(gameObject);
            core.objectTouched(objectId);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loader.setController(this);

        GameMode gameMode = GameManager.getInstance().getGameMode();

        Map<String, GameObject> gameObjects = gameMode.getGameObjects();

        GameObject bonusObject = gameObjects.get("bonus");
        GameObject malusObject = gameObjects.get("malus");

        actualScore.textProperty().bind(GameManager.getInstance().getActualScore().asString());

        finalScore.setText(String.valueOf(gameMode.getEndingScore()));
        bonusPoints.setText(String.valueOf(bonusObject.getPoints()));
        malusPoints.setText(String.valueOf(malusObject.getPoints()));
        mysteryPoints.setText("Huh ? :)");

        String imagesPath = System.getProperty("user.dir") + File.separator +
                Configuration.getInstance().get("MODES_PATH") + File.separator +
                gameMode.getName() + File.separator;

        String bonusImagePath = imagesPath + "bonus.png";
        String malusImagePath = imagesPath + "malus.png";
        String mysteryImagePath = imagesPath + "mystery.png";

        File bonusImageFile = new File(bonusImagePath);
        File malusImageFile = new File(malusImagePath);
        File mysteryImageFile = new File(mysteryImagePath);

        String bonusImageUri = bonusImageFile.toURI().toString();
        String malusImageUri = malusImageFile.toURI().toString();
        String mysteryImageUri = mysteryImageFile.toURI().toString();

        Image bonusImage = new Image(bonusImageUri);
        Image malusImage = new Image(malusImageUri);
        Image mysteryImage = new Image(mysteryImageUri);

        ImageView bonusImageView = new ImageView(bonusImage);
        ImageView malusImageView = new ImageView(malusImage);
        ImageView mysteryImageView = new ImageView(mysteryImage);

        bonusImagePane.getChildren().add(bonusImageView);
        malusImagePane.getChildren().add(malusImageView);
        mysteryImagePane.getChildren().add(mysteryImageView);

    }

    @Override
    public void stop() {
        core.stop();
    }
}
