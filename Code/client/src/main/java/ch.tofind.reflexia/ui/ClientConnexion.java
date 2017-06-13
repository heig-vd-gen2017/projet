package ch.tofind.reflexia.ui;

import ch.tofind.reflexia.core.Core;
import ch.tofind.reflexia.network.NetworkProtocol;

import ch.tofind.reflexia.utils.Network;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ClientConnexion extends Application {

    private static FXMLLoader loader = new FXMLLoader();

    private static final String FXML_FILE = "ui/ClientConnexion.fxml";

    private static Core core = Core.getInstance();

    private Stage stageGlobal;

    private ObservableList<String> networkInterfaces = FXCollections.observableArrayList(new ArrayList<>(Network.getIPv4Interfaces().keySet()));

    @FXML
    TextField textFieldPseudo;

    @FXML
    TextField textFieldServerIP;

    @FXML
    TextField textFieldUnicastPort;

    @FXML
    ChoiceBox<String> choiceBoxNetworkInterface;

    @FXML
    TextField textFieldMulticastAddress;

    @FXML
    TextField textFieldMulticastPort;

    @FXML
    Button buttonConnect;

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

        choiceBoxNetworkInterface.setItems(networkInterfaces);
        choiceBoxNetworkInterface.getSelectionModel().selectFirst();

        // Temporaire, à supprimer !!
        textFieldPseudo.setText("test");
        textFieldServerIP.setText("localhost");

        // Set interface
        textFieldMulticastAddress.setText(NetworkProtocol.MULTICAST_ADDRESS);
        textFieldMulticastPort.setText(String.valueOf(NetworkProtocol.MULTICAST_PORT));
        textFieldUnicastPort.setText(String.valueOf(NetworkProtocol.UNICAST_PORT));

        // Set button connect
        buttonConnect.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {

                core.connection(textFieldPseudo.getText(), textFieldServerIP.getText(), textFieldUnicastPort.getText(), choiceBoxNetworkInterface.getValue(), textFieldMulticastAddress.getText(), textFieldMulticastPort.getText());

                ClientGame clientGame = new ClientGame();

                try {
                    clientGame.start(ClientGame.classStage);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Stage stage = (Stage) buttonConnect.getScene().getWindow();
                stage.close();
            }
        });
    }

    @Override
    public void stop() {
        core.stop();
    }
}
