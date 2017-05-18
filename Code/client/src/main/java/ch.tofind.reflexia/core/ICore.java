package ch.tofind.reflexia.core;

import java.net.InetAddress;

public interface ICore {

    abstract void sendUnicast(InetAddress hostname, int port, String message);

    abstract void sendMulticast(String message);

    abstract void stop();
}
