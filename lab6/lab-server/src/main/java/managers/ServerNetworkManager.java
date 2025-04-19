package managers;

import java.io.IOException;
import java.net.*;
import java.util.logging.Logger;

public class ServerNetworkManager {
    public static final Logger logger = Logger.getLogger("NetworkLogger");
    private final int port;
    private InetSocketAddress host;
    private ServerSocket server;

    public ServerNetworkManager(int port) {
        this.port = port;
        try {
            this.host = new InetSocketAddress(port);
            this.server = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e); // Что-то сделать
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
