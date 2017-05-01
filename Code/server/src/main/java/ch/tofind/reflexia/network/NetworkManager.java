package ch.tofind.reflexia.network;

import ch.tofind.reflexia.game.GameObject;
import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkManager implements Protocol {

    /*
    private ServerSocket socket;

    public NetworkManager() throws IOException {
        socket = new ServerSocket(DEFAULT_PORT);


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
        GameObject gameObjectReceived = gsonMotor.fromJson(spirte, GameObject.class);
        System.out.println(gameObjectReceived.getExampleSprite());
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

    public void handleConnection() throws IOException {
        Socket gamerSocket;

        while (true) {
            gamerSocket = socket.accept();

            new Thread(new GameHandler(gamerSocket)).start();
        }
    }

    private boolean isUsernameUsed(String username) {
        return username.equals(usernameUsed);
    }

    */
}
