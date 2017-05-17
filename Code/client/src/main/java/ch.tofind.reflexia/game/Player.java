package ch.tofind.reflexia.game;

import java.net.InetAddress;

/**
 * @brief This class represents a player
 */
public class Player {

    //! Name of the player
    private String pseudo;
    private String ipAddress;
    private int port;

    /**
     * @brief Create a player
     * @param pseudo Name of the player
     * @param ipAddress IP address to which the player wants to connect to
     * @param port Port the player want to connect to
     */
    public Player(String pseudo, String ipAddress, int port) {
        this.pseudo = pseudo;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    /**
     * @brief Get the player's name
     * @return The player's name
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * @brief Get the player's IP address he wants to connect to
     * @return The player's IP address he want to connect to
     */
    public String getIpAddress() { return ipAddress; }

    /**
     * @brief Get the player's port he wants to connect to
     * @return The player's port he wants to connect to
     */
    public int getPort() { return port; }

    @Override
    public String toString() {

        return "Player"                      + '\n' + '\t' +
                "Pseudo..........: " + pseudo + '\n' + '\t' +
                "IP address......: " + ipAddress + '\n' + '\t' +
                "Port............: " + port;
    }
}
