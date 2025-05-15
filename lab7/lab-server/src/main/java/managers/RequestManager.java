package managers;

import network.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class RequestManager {
    public static final Logger logger = Logger.getLogger("RequestLogger");

    public Request readRequest(Socket client) {
        logger.info("Beginning of processing a request");
        try {
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            return (Request) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.warning("Connection was lost");
            return null;
        }
    }
}
