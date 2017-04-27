package client;

import com.google.gson.Gson;
import errors.FullGameException;
import errors.UsernameUsedException;
import game.Configuration;
import game.DataGame;
import game.Spirte;
import protocol.GamerProtocol;

import java.io.*;
import java.net.Socket;

/**
 * Created by luca on 26.04.17.
 */
public class Gamer implements GamerProtocol {

    private String username;
    private String ipAddress;

    private Configuration configuration;
    private DataGame dataGame;

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    private Gson gsonMotor;

    /**
     * This function allows to load the game configuration
     *
     * @param configuration the configuration in JSON
     */
    private void loadConfiguration(String configuration) {
        this.configuration = gsonMotor.fromJson(configuration, Configuration.class);
    }

    private void loadDataGame(String dataGame) {
        this.dataGame = gsonMotor.fromJson(dataGame, DataGame.class);
    }

    public Gamer(String username, String ipAddress) {
        this.username  = username;
        this.ipAddress = ipAddress;

        gsonMotor = new Gson();
    }

    public void joinGame(String username) throws UsernameUsedException, FullGameException, IOException {
        String serverResponse;

        socket = new Socket(ipAddress, DEFAULT_PORT);

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), DEFAULT_CHARSET));
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), DEFAULT_CHARSET));

        writer.println(CMD_JOIN);
        writer.println(username);
        writer.flush();

        serverResponse = reader.readLine();

        switch (serverResponse) {

            case CMD_USERNAME_USED :
                throw new UsernameUsedException("Username already used!");

            case CMD_FULL_GAME :
                throw new FullGameException("Game full!");

            case CMD_JOINED :
                loadConfiguration(reader.readLine());
                break;
        }
    }

    public void updatedDataGame() {
        writer.println(CMD_UPDATED);
        writer.flush();
    }

    public void sendClickedSpirte(Spirte spirte) {
        writer.println(CMD_SEND);
        writer.println(gsonMotor.toJson(spirte));
        writer.flush();
    }

    public void quitGame() {
        writer.println(CMD_QUIT);
        writer.flush();
    }
}
