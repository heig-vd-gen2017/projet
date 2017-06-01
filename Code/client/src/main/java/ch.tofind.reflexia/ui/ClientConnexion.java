package ch.tofind.reflexia.ui;

import ch.tofind.reflexia.core.Core;
import ch.tofind.reflexia.network.NetworkProtocol;
import ch.tofind.reflexia.game.GameManager;

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
    TextField textFieldMulticastAddress;

    @FXML
    TextField textFieldMulticastPort;

    @FXML
    TextField textFieldIpAddress;

    @FXML
    TextField textFieldUnicastPort;

    @FXML
    Button  buttonConnect;

    public void start(Stage stage) throws IOException {
        stageGlobal = stage;
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

        stageGlobal.setTitle("Reflexia");
        stageGlobal.setResizable(false);
        stageGlobal.setScene(scene);

        stageGlobal.show();
    }


    @FXML
    private void initialize() {

        // Set button connect
        buttonConnect.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {

                core.connection(textFieldPseudo.getText(), textFieldMulticastAddress.getText(), textFieldMulticastPort.getText(), textFieldIpAddress.getText(), textFieldUnicastPort.getText());

                ClientGame cg = new ClientGame();
                try {
                    cg.start(ClientGame.classStage);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Stage stage = (Stage) buttonConnect.getScene().getWindow();
                stage.close();
            }
        });

        // Set interface
        textFieldMulticastAddress.setText(NetworkProtocol.MULTICAST_ADDRESS);
        textFieldMulticastPort.setText(String.valueOf(NetworkProtocol.MULTICAST_PORT));
        textFieldUnicastPort.setText(String.valueOf(NetworkProtocol.UNICAST_PORT));
    }
}
