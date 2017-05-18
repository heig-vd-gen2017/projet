package ch.tofind.reflexia.ui;

import ch.tofind.reflexia.core.Core;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Denise on 18.05.2017.
 */
public class ClientGame extends Application {

    private static FXMLLoader loader = new FXMLLoader();

    private static final String FXML_FILE = "ui/ClientGame.fxml";

    private static Core core = Core.getInstance();

    public void start(Stage stage) throws IOException {
        
    }

}
