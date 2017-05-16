package ch.tofind.reflexia.ui;

import ch.tofind.reflexia.game.GameManager;
import ch.tofind.reflexia.mode.GameMode;
import ch.tofind.reflexia.mode.GameModeManager;
import ch.tofind.reflexia.mode.GameObject;
import ch.tofind.reflexia.utils.Network;
import ch.tofind.reflexia.utils.Serialize;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.Event;
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
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ServerConfiguration extends Application {

    private static FXMLLoader loader = new FXMLLoader();

    private static final String FXML_FILE = "ui/ServerConfiguration.fxml";

    private static GameManager gameManager = GameManager.getInstance();

    private int serverPort;

    private InetAddress ipAddress;

    private String resetDate;

    private ObservableMap<String, GameMode> modes = FXCollections.observableHashMap();

    //private ObservableList<Map.Entry<String, GameMode>> modes = FXCollections.observableArrayList(GameModeManager.getInstance().getGameModes()));

    private ObservableList<String> ipAddressString = FXCollections.observableArrayList(new ArrayList<>(Network.getIPv4Interfaces().keySet()));

    @FXML
    private ChoiceBox<Map.Entry<String, GameMode>> choiceBoxModeName;

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
        //gameManager.setGameMode([LE MODE SÉLECTIONNÉ PAR L'UTILISATEUR]);
        buttonSaveMode.setDisable(true);
        choiceBoxModeName.setDisable(true);
        buttonAcceptConnexions.setDisable(false);

    }

    @FXML
    private void acceptConnexions(MouseEvent event) {
        String ipAddressString = choiceBoxIPAddress.getValue();
        String serverPortString = choiceBoxIPAddress.getValue();

        serverPort = Integer.valueOf(serverPortString);
        ipAddress = Serialize.unserialize(ipAddressString, InetAddress.class);

        gameManager.setIpAddress(ipAddress);
        gameManager.setPort(Integer.valueOf(serverPort));

        gameManager.acceptConnection();

        buttonAcceptConnexions.setDisable(true);
        buttonBeginGame.setDisable(false);
    }

    @FXML
    private void beginGame(MouseEvent event) {
        buttonStopGame.setDisable(false);
        System.out.println("Game about to start...");
        buttonBeginGame.setDisable(true);

        gameManager.start();
    }

    @FXML
    private void stopGame(MouseEvent event) {
        System.out.println("Game about to stop...");

        gameManager.stop();
    }

    @FXML
    private void setResetDate(MouseEvent event) {
        LocalDate localDate = datePickerResetScores.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        System.out.println(date);
    }

    @FXML
    private void initialize() {

        modes.putAll(GameModeManager.getInstance().getGameModes());

        choiceBoxModeName = new ChoiceBox<>();

        for (Map.Entry<String, GameMode> mode : GameModeManager.getInstance().getGameModes().entrySet()) {
            choiceBoxModeName.getItems().add(mode);
        }

        choiceBoxModeName.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Map.Entry<String, GameMode>>() {
            @Override
            public void changed(ObservableValue<? extends Map.Entry<String, GameMode>> observableValue, Map.Entry<String, GameMode> number, Map.Entry<String, GameMode> t1) {

            }
        });
        choiceBoxModeName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                textFieldModeName.setText(newValue);
                textFieldInitialScore.setText(Integer.toString(GameModeManager.getInstance().getGameModes().get(newValue).getStartingScore()));
                textFieldScoreToGet.setText(Integer.toString(GameModeManager.getInstance().getGameModes().get(newValue).getEndingScore()));
                textFieldNumberOfRounds.setText(Integer.toString(GameModeManager.getInstance().getGameModes().get(newValue).getRounds()));
                List<GameObject> gameObjects = GameModeManager.getInstance().getGameModes().get(newValue).getGameObjects().getGameObjects();
                // Objets en dur, pas super!
                if (gameObjects.get(0).getEnabled()) checkBoxBonus.setSelected(true);
                if (gameObjects.get(1).getEnabled()) checkBoxMalus.setSelected(true);
                if (gameObjects.get(2).getEnabled()) checkBoxMystery.setSelected(true);
            }
        });
        choiceBoxModeName.getSelectionModel().selectFirst();
        choiceBoxIPAddress.setItems(ipAddressString);
        choiceBoxIPAddress.getSelectionModel().selectFirst();
        buttonAcceptConnexions.setDisable(true);
        buttonBeginGame.setDisable(true);
        buttonStopGame.setDisable(true);
    }
}