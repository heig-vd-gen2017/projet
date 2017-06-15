package ch.tofind.reflexia.core;

import java.net.InetAddress;
import java.util.ArrayList;

public interface ICore {

    /**
     * Send message to hostname by unicast.
     * @param hostname Where to send the message.
     * @param port The port of the host
     * @param message The message to send.
     */
    abstract void sendUnicast(InetAddress hostname, int port, String message);

    /**
     * Send message by multicast.
     * @param message The message to send.
     */
    abstract void sendMulticast(String message);

    /**
     * Start the server.
     * @param multicastAddress The multicast address to use for communication.
     * @param multicastPort The multicast port to use for communication.
     * @param interfaceToUse The network interface to use for multicast.
     * @param unicastPort The unicast address to use for communication.
     */
    abstract void start(String multicastAddress, int multicastPort, InetAddress interfaceToUse, int unicastPort);

    /**
    * Stop the server.
    */
    abstract void stop();

    /**
     * Execute the command on the core.
     * @param command The command to execute.
     * @param args The arguments of the command.
     * @return Returns the result of the command.
     */
    abstract String execute(String command, ArrayList<Object> args);
}
