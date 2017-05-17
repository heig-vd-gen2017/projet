package ch.tofind.reflexia.ui;

import ch.tofind.reflexia.core.Core;
import ch.tofind.reflexia.game.GameManager;
import ch.tofind.reflexia.mode.GameModeManager;
import ch.tofind.reflexia.mode.GameObject;
import ch.tofind.reflexia.utils.Network;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class ServerConfiguration extends Application {

    private static FXMLLoader loader = new FXMLLoader();

    private static final String FXML_FILE = "ui/ServerConfiguration.fxml";

    private static Core core = Core.getInstance();

    private ObservableList<String> modesString = FXCollections.observableArrayList(new ArrayList<>(GameModeManager.getInstance().getGameModes().keySet()));

    private ObservableList<String> ipAddressString = FXCollections.observableArrayList(new ArrayList<>(Network.getIPv4Interfaces().keySet()));

    @FXML
    private ChoiceBox<String> choiceBoxModeName;

    @FXML
    private ChoiceBox<String> choiceBoxIPAddress;

    @FXML
    private TextField textFieldModeName;

    @FXML
    private TextField textFieldInitialScore;

    @FXML
    private TextField textFieldScoreToGet;

    @FXML
    private TextField textFieldNumberOfRounds;

    @FXML
    private CheckBox checkBoxBonus;

    @FXML
    private CheckBox checkBoxMalus;

    @FXML
    private CheckBox checkBoxMystery;

    @FXML
    private TextField textFieldServerPort;

    @FXML
    private DatePicker datePickerResetScores;

    @FXML
    private Button buttonSaveMode;

    @FXML
    private Button buttonAcceptConnexions;

    @FXML
    private Button buttonBeginGame;

    @FXML
    private Button buttonStopGame;

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
    private void saveMode(MouseEvent event) {

        // Change the interface
        buttonSaveMode.setDisable(true);
        choiceBoxModeName.setDisable(true);
        buttonAcceptConnexions.setDisable(false);
        choiceBoxIPAddress.setDisable(false);
        textFieldServerPort.setDisable(false);

        // Tells the Core what mode was selected
        core.setGameMode(buttonSaveMode.getText());
    }

    @FXML
    private void acceptConnexions(MouseEvent event) {

        // Change the interface
        choiceBoxIPAddress.setDisable(true);
        textFieldServerPort.setDisable(true);
        buttonAcceptConnexions.setDisable(true);
        buttonBeginGame.setDisable(false);

        // Tells the Core what network settings were set
        core.setNetworkSettings(choiceBoxIPAddress.getValue(), textFieldServerPort.getText());

    }

    @FXML
    private void beginGame(MouseEvent event) {

        // Change interface
        buttonStopGame.setDisable(false);
        buttonBeginGame.setDisable(true);

        // Tells the Core that we want to game to start
        core.beginGame();

    }

    @FXML
    private void endGame(MouseEvent event) {

        // Tells the Core that we want to start the game
        core.endGame();

        // Reset the interface
        initialize();
    }

    @FXML
    private void resetScores(MouseEvent event) {
        LocalDate localDate = datePickerResetScores.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);

        // Tells the Core that we want to reset the scores
        core.resetScores(date);
    }

    @FXML
    private void initialize() {

        choiceBoxModeName.setItems(modesString);

        choiceBoxModeName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                // Change the interface
                textFieldModeName.setText(newValue);
                textFieldInitialScore.setText(Integer.toString(GameModeManager.getInstance().getGameModes().get(newValue).getStartingScore()));
                textFieldScoreToGet.setText(Integer.toString(GameModeManager.getInstance().getGameModes().get(newValue).getEndingScore()));
                textFieldNumberOfRounds.setText(Integer.toString(GameModeManager.getInstance().getGameModes().get(newValue).getRounds()));

                List<GameObject> gameObjects = GameModeManager.getInstance().getGameModes().get(newValue).getGameObjects().getGameObjects();

                checkBoxBonus.setSelected(gameObjects.get(0).getEnabled());
                checkBoxMalus.setSelected(gameObjects.get(1).getEnabled());
                checkBoxMystery.setSelected(gameObjects.get(2).getEnabled());
            }
        });

        choiceBoxModeName.getSelectionModel().selectFirst();

        choiceBoxIPAddress.setItems(ipAddressString);
        choiceBoxIPAddress.getSelectionModel().selectFirst();

        // Set the interface
        choiceBoxModeName.setDisable(false);
        buttonSaveMode.setDisable(false);

        choiceBoxIPAddress.setDisable(true);
        textFieldServerPort.setDisable(true);
        buttonAcceptConnexions.setDisable(true);
        buttonBeginGame.setDisable(true);
        buttonStopGame.setDisable(true);
    }
}