package managers;

import java.io.IOException;
import java.net.*;
import java.util.logging.Logger;

public class NetworkManager {
    // ssh -p 2222 s467898@se.ifmo.ru -Y -L 46789:helios:46789
    public static final Logger logger = Logger.getLogger("NetworkLogger");
    private InetSocketAddress host;
    private ServerSocket server;

    public NetworkManager(int port) {
        try {
            this.host = new InetSocketAddress(port);
            this.server = new ServerSocket(port);
        } catch (IOException e) {
            logger.info("Не удалось открыть сокет");
            System.exit(0);
        }
    }

    public Socket connectToClient() {
        try {
            logger.info("Попытка подключения к клиенту");
            Socket clientSocket = server.accept();
            logger.info("Успешное подключение к клиенту");
            return clientSocket;
        } catch (IOException e) {
            logger.warning("Не удалось подключиться к клиенту");
        }
        return null;
    }
}
