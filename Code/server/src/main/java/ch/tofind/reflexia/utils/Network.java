package ch.tofind.reflexia.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

/**
 * @brief the purpose of this helper class is to collect the network interfaces of a machine
 */
public class Network {
    
    /**
     * @brief gets the list of network interfaces
     * @return an ArrayList of network interface
     */
    static public ArrayList<NetworkInterface> getNetworkInterfaces() {

        ArrayList<NetworkInterface> networkInterfaces = new ArrayList<>();

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface networkInterface : Collections.list(interfaces)) {

                // We shouldn't care about loopback addresses
                if (networkInterface.isLoopback())
                    continue;

                // We shouldn't care about down links
                if (!networkInterface.isUp())
                    continue;

                networkInterfaces.add(networkInterface);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return networkInterfaces;
    }

    /**
     * @brief gets the available ipv4 network interfaces
     * @return MAP of name inet adress of available ipv4 addresses
     */
    static public Map<String, InetAddress> getIPv4Interfaces() {

        ArrayList<NetworkInterface> networkInterfaces = getNetworkInterfaces();

        Map<String, InetAddress> availableIPv4Interfaces = new HashMap<>();

        for (NetworkInterface networkInterface : networkInterfaces) {

            String interfaceName = networkInterface.getName();

            ArrayList<InetAddress> inetAddresses = Network.getInetAddresses(networkInterface);

            for (InetAddress address : inetAddresses) {

                if (address instanceof Inet4Address) {
                    availableIPv4Interfaces.put(interfaceName, address);
                }
            }
        }

        return availableIPv4Interfaces;
    }

    /**
     * @brief gets the inet adresses
     * @param networkInterface
     * @return list of addresses
     */
    static public ArrayList<InetAddress> getInetAddresses(NetworkInterface networkInterface) {
        Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
        return Collections.list(addresses);
    }


}