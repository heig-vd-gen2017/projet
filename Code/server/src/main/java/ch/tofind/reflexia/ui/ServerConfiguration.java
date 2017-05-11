package ch.tofind.reflexia.ui;

import ch.tofind.reflexia.game.GameManager;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

import ch.tofind.reflexia.game.Mode;

public class ServerConfiguration extends Application {

    //!
    private static FXMLLoader loader = new FXMLLoader();

    //! FXML file to use for the view.
    private static final String FXML_FILE = "ui/ServerConfiguration.fxml";

    private GameManager gameManager;
    private boolean choseExistingMode;
    private Mode newMode;
    private String serverPort;
    private String ipAddress;
    private String resetDate;


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
    private CheckBox checkBoxBonus;

    @FXML
    private CheckBox checkBoxMalus;

    @FXML
    private CheckBox checkBoxMystery;

    @FXML
    private TextField textFieldServerPort;

    @FXML
    private ChoiceBox<String> choiceBoxIPAddress;

    @FXML
    private DatePicker datePickerResetScores;

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

        choseExistingMode = false;
        choiceBoxIPAddress = new ChoiceBox<>();
        /*
        for (int i = 0; i < ???.size; i++)
            choiceBoxIPAddress.getItems().add(???[i]);
        */
        choiceBoxModeName = new ChoiceBox<>();
        /*
        for (int i = 0; i < GameMode.modes.size(); i++)
            choiceBoxModeName.getItems().add(modes[i]);
        */

        stage.show();
    }


    // TODO: tests supplémentaires pour savoir si quelque chose a été sélectionné
    @FXML
    private void chosenExistingMode(Event event) {
        choseExistingMode = true;
    }

    @FXML
    private void saveMode(MouseEvent event) {
        newMode = new Mode();

        if (choseExistingMode) {
            newMode = choiceBoxModeName.getValue();

        } else {
            newMode.setName(textFieldModeName.getText());
            newMode.setStartingScore(Integer.parseInt(textFieldInitialScore.getText()));
            newMode.setEndingScore(Integer.parseInt(textFieldScoreToGet.getText()));
            newMode.setRounds(Integer.parseInt(textFieldNumberOfRounds.getText()));
            newMode.setMysteryObjects((boolean)checkBoxMystery.isSelected());
            newMode.setBonusObjects((boolean)checkBoxBonus.isSelected());
            newMode.setMalusObjects((boolean)checkBoxMalus.isSelected());

            System.out.println("New mode saved!");
        }

    }

    @FXML
    private void acceptConnexions(MouseEvent event) {
        System.out.println("Accepting connexions");
        ipAddress = choiceBoxIPAddress.getValue();
        serverPort = textFieldServerPort.getText();
    }

    @FXML
    private void beginGame(MouseEvent event) {
        System.out.println("Game about to start...");
        /*
        TODO
        gameManager.start();
        */
    }

    @FXML
    private void stopGame(MouseEvent event) {
        System.out.println("Game about to stop...");
        /*
        TODO
        gameManager.stop();
        */
    }

    @FXML
    private void setResetDate(MouseEvent event) {
        resetDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(datePickerResetScores.getValue());
        System.out.println("Date of reset selected: " + resetDate);
    }
}