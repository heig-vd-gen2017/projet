package ch.tofind.reflexia.network;

import java.net.InetAddress;

/**
 * @brief network protocol
 */
public final class NetworkProtocol {

    public static final InetAddress interfaceToUse = null;

    public static final Integer MULTICAST_PORT = 9999;
    public static final Integer UNICAST_PORT = 9998;

    public static final String MULTICAST_ADDRESS    = "239.192.0.2";
    public static final String END_OF_LINE          = "\n";
    public static final String END_OF_COMMAND       = "END_OF_COMMAND";
    public static final String END_OF_COMMUNICATION = "END_OF_COMMUNICATION";

}
