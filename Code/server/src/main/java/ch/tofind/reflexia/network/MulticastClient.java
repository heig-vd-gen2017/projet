package ch.tofind.reflexia.network;

import ch.tofind.reflexia.core.Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @brief This class receives data from the server by multicast.
 */
public class MulticastClient implements Runnable {

    //! port number
    private int port;

    //! multicast group ip address
    private InetAddress multicastGroup;

    //! socket object
    private MulticastSocket socket;

    //! state running
    private boolean running;

    /**
     * @brief multicast client class constructor
     * @param multicastAddress
     * @param port
     * @param interfaceToUse
     */
    public MulticastClient(String multicastAddress, int port, InetAddress interfaceToUse) {
        this.port = port;
        this.running = false;

        try {
            socket = new MulticastSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.setInterface(interfaceToUse);
            socket.setLoopbackMode(false);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            multicastGroup = InetAddress.getByName(multicastAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            socket.joinGroup(multicastGroup);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief run method
     */
    @Override
    public void run() {

        running = true;

        // Get the response
        byte[] reponse = new byte[8192];
        DatagramPacket in = new DatagramPacket(reponse, reponse.length);

        String command;
        String input = null;

        while (running) {

            // Store the commands sent by the client
            ArrayList<String> commands = new ArrayList<>();

            try {

                socket.receive(in);

                byte[] rawData = in.getData();

                String data = new String(rawData).trim();

                BufferedReader reader = new BufferedReader(new StringReader(data));

                input = reader.readLine();

                while ((input != null) && !Objects.equals(input, NetworkProtocol.END_OF_COMMAND)) {
                    commands.add(input);
                    input = reader.readLine();
                }

                reader.close();

            } catch (SocketException e) {
                // Do nothing and continue
                continue;
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Get the requested command
            command = commands.remove(0);

            // Prepare the args to send to the controller
            ArrayList<Object> args = new ArrayList<>(commands);

            // Send the command and its arguments to the controller and get the result
            String result = Core.getInstance().execute(command, args);

            if (!Objects.equals(result, "")) {
                send(result + NetworkProtocol.END_OF_LINE);
            }
        }
    }

    /**
     * @brief stop method
     */
    public void stop() {
        running = false;
        socket.close();
    }

    /**
     * sends the message
     * @param message
     */
    public void send(String message) {

        // Transforms the message in bytes
        byte[] messageBytes = message.getBytes();

        // Prepare the packet
        DatagramPacket out = new DatagramPacket(messageBytes, messageBytes.length, multicastGroup, port);

        // Send the packet
        try {
            socket.send(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
