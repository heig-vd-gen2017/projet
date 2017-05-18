package ch.tofind.reflexia.ui;

import ch.tofind.reflexia.core.Core;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

public class ClientGame extends Application {

    private static Core core = Core.getInstance();

    private static FXMLLoader loader = new FXMLLoader();

    private static final String FXML_FILE_2 = "ui/ClientGame.fxml";

    static Stage classStage = new Stage();

    @FXML
    Button buttonClose;

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



    @Override
    public void stop() {
        core.stop();
    }

}
