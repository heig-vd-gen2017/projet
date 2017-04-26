package server;

import com.google.gson.Gson;
import game.Configuration;
import game.DataGame;
import game.Spirte;
import protocol.Protocol;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by luca on 26.04.17.
 */
public class GameHandler implements Runnable, Protocol {

    private static Configuration configuration = new Configuration("config");
    private static DataGame dataGame = new DataGame("data");
    private static ArrayList<Spirte> spirtes = new ArrayList<>();

    private Socket gameSocket;

    private BufferedReader reader;
    private PrintWriter writer;

    private Gson gsonMotor;

    private String usernameUsed = "James Bond";
    private boolean gameFull = true;

    private boolean isUsernameUsed(String username) {
        return username.equals(usernameUsed);
    }

    private void handleCmdJoin(String usernameReceived) {
        if (gameFull) {
            writer.println(CMD_FULL_GAME);
            writer.flush();
            return;
        }

        if (isUsernameUsed(usernameReceived)) {
            writer.println(CMD_USERNAME_USED);
            writer.flush();
            return;
        }

        writer.println(CMD_JOINED);
        writer.println(gsonMotor.toJson(configuration));
        writer.flush();
    }

    private void handleCmdUpdated() {
        // A coder dans les prochaines itérations
        System.out.println("Received UPDATED command");
    }

    private void handleCmdSend(String spirte) {
        Spirte spirteReceived = gsonMotor.fromJson(spirte, Spirte.class);
        System.out.println(spirteReceived.getExampleSpirte());
    }

    public GameHandler(Socket gameSocket) throws IOException {
        this.gameSocket = gameSocket;

        this.reader = new BufferedReader(new InputStreamReader(gameSocket.getInputStream(), DEFAULT_CHARSET));
        this.writer = new PrintWriter(new OutputStreamWriter(gameSocket.getOutputStream(), DEFAULT_CHARSET));

        gsonMotor = new Gson();
    }

    public void run() {
        String gamerMessage;

        while (true) {
            try {
                gamerMessage = reader.readLine();

                switch (gamerMessage) {
                    case CMD_JOIN :
                        handleCmdJoin(reader.readLine());
                        break;

                    case CMD_UPDATED :
                        handleCmdUpdated();
                        break;

                    case CMD_SEND :
                        handleCmdSend(reader.readLine());
                        break;

                    default:
                        writer.println(CMD_END_OF_GAME);
                        writer.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            gameFull = false;
        }
    }
}
