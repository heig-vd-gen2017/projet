package ch.tofind.reflexia.ui;

import ch.tofind.reflexia.core.Core;
import ch.tofind.reflexia.game.Player;
import ch.tofind.reflexia.network.NetworkProtocol;
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

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.Map;

public class ClientConnexion extends Application {

    private static FXMLLoader loader = new FXMLLoader();

    private static final String FXML_FILE = "ui/ClientConnexion.fxml";
    private static final String FXML_FILE_2 = "ui/ClientGame.fxml";

    private static Core core = Core.getInstance();

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
    }

    @FXML
    private void connection(MouseEvent event) {
        core.connection(textFieldPseudo.getText(), textFieldMulticastAddress.getText(), textFieldMulticastPort.getText(), textFieldIpAddress.getText(), textFieldUnicastPort.getText());
    }

    @FXML
    private void initialize() {

        // Set button connect
        buttonConnect.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
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

                Stage stage = new Stage();

                stage.setTitle("Reflexia");
                stage.setResizable(false);
                stage.setScene(scene);

                stage.show();
            }
        });

        // Set interface
        textFieldMulticastAddress.setText(NetworkProtocol.DEFAULT_MULTICAST_ADDRESS);
        textFieldMulticastPort.setText(String.valueOf(NetworkProtocol.DEFAULT_MULTICAST_PORT));
        textFieldUnicastPort.setText(String.valueOf(NetworkProtocol.DEFAULT_UNICAST_PORT));
    }
}
