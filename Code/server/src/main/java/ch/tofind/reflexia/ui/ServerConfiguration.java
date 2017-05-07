package ch.tofind.reflexia.ui;

import ch.tofind.reflexia.game.GameManager;
import com.sun.corba.se.spi.activation.Server;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.BatchUpdateException;
import java.util.ResourceBundle;

import ch.tofind.reflexia.game.Mode;
import ch.tofind.reflexia.database.DatabaseManager;
import ch.tofind.reflexia.network.NetworkManager;


/**
 * @brief UI controller
 *
 * Controller meant to interact with the interface
 */
public class ServerConfiguration extends Application implements Initializable {

    //!
    private static FXMLLoader loader = new FXMLLoader();

    //! FXML file to use for the view
    private static final String FXML_FILE = "ui/ServerConfiguration.fxml";


    //! Selection of mode and initial requirements
    @FXML
    private ChoiceBox<Mode> choiceBoxModeName;

    @FXML
    private TextField textFieldModeName;

    @FXML
    private TextField textFieldInitialScore;

    @FXML
    private TextField textFieldScoreToGet;

    @FXML
    private TextField textFieldNumberOfRounds;

    @FXML
    private Checkbox tickBoxBonus;

    @FXML
    private Checkbox tickBoxMalus;

    @FXML
    private Checkbox tickBoxMystery;

    @FXML
    private Button buttonSaveMode;

    //! Reset scores
    @FXML
    private DatePicker datePickerResetScores;

    @FXML
    private Button buttonResetScores;

    //! Network configurations
    @FXML
    private TextField textFieldServerPort;

    @FXML
    private ChoiceBox choiceBoxIPAddress;

    //! Accept players
    @FXML
    private Button buttonAcceptConnexions;

    //! Begin and end game
    @FXML
    private Button buttonBeginGame;

    @FXML
    private Button buttonStopGame;


    public void start(Stage stage) {
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
        stage.setScene(scene);
        stage.setResizable(false);

        // Add modes to choice box!!!
        // choiceBoxModeName = new ChoiceBox(FXCollections.observableArrayList(mode1.getName(), mode2.getName()));

        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loader.setController(this);
    }

    public ServerConfiguration() {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(FXML_FILE));
        loader.setRoot(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        buttonSaveMode

        String modeName = textFieldModeName.getSelectedText();

    }

}