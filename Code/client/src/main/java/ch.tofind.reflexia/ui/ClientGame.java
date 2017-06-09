package ch.tofind.reflexia.ui;

import ch.tofind.reflexia.core.Core;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    TableView<String> tableViewScores;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loader.setController(this);
    }

    @FXML
    public void closeWindow(MouseEvent event) {
        Stage stage = (Stage)buttonClose.getScene().getWindow();
        stage.close();
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
    public void stop() {
        core.stop();
    }
}
