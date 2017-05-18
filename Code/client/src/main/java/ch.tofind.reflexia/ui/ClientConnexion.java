package ch.tofind.reflexia.ui;

import ch.tofind.reflexia.core.Core;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ClientConnexion extends Application {

    private static FXMLLoader loader = new FXMLLoader();

    private static final String FXML_FILE = "ui/ClientConnexion.fxml";

    private static Core core = Core.getInstance();

    private Stage stageGlobal;

    @FXML
    TextField textFieldPseudo;

    @FXML
    TextField textFieldIpAddress;

    @FXML
    TextField textFieldPort;

    @FXML
    Button  buttonConnect;

    @FXML
    TextField textFieldMulticastAddress;

    @FXML
    TextField textFieldMulticastPort;

    public void start(Stage stage) throws IOException {
        URL fileURL = getClass().getClassLoader().getResource(FXML_FILE);

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

        stage.setTitle("Reflexia");
        stage.setResizable(false);
        stage.setScene(scene);

        stage.show();
        stageGlobal = stage;
    }

    @FXML
    private void connection(MouseEvent event) {
        core.connection(textFieldPseudo.getText(), textFieldMulticastAddress.getText(), textFieldMulticastPort.getText(), textFieldIpAddress.getText(), textFieldPort.getText());
        stageGlobal.hide();
    }

    @FXML
    private void initialize() {
        buttonConnect.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                ClientGame cg = new ClientGame();
                try {
                    cg.start(ClientGame.classStage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
