package ch.tofind.reflexia;

import ch.tofind.reflexia.ui.ServerConfiguration;

import javafx.application.Application;

import java.io.IOException;

public class Reflexia {

    public static void main(String[] args) throws IOException {

        Application.launch(ServerConfiguration.class, args);

    }
}
