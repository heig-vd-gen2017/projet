package ch.tofind.reflexia.ui;

import ch.tofind.reflexia.core.Core;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ClientGame extends Application {

    private static Core core = Core.getInstance();

    private static FXMLLoader loader = new FXMLLoader();

    private static final String FXML_FILE_2 = "ui/ClientGame.fxml";

    static Stage classStage = new Stage();

    @FXML
    Button buttonClose;

    @FXML
    Pane paneGame;

    @FXML
    TableView<String> tableViewScores;

    public void start(Stage stage) throws IOException {
        classStage = stage;

        URL fileURL = getClass().getClassLoader().getResource(FXML_FILE_2);

        if (fileURL == null) {
            throw new NullPointerException("FXML file not found.");
        }

        Parent root = null;

        try {
            root = loader.load(fileURL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root);

        classStage.setTitle("Reflexia");
        classStage.setResizable(false);
        classStage.setScene(scene);

        classStage.show();
    }

    @FXML
    public void closeWindow(MouseEvent event) {
        Stage stage = (Stage)buttonClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void newObject(MouseEvent event) {
        addObject(-20, 0);
    }

    /**
     *
     * @param posX maxPosX = 250, minPosX = -20
     * @param posY maxPosY = 230, minPosY = 0
     */
    public void addObject(int posX, int posY) {
        ImageView iv = new ImageView(new Image("http://icons.iconarchive.com/icons/kidaubis-design/cool-heroes/128/Ironman-icon.png"));
        iv.relocate(posX, posY);
        paneGame.getChildren().add(iv);
    }

    @Override
    public void stop() {
        core.stop();
    }
}
