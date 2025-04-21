import managers.*;
import network.Request;

import network.Response;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

public final class Server extends Thread {
    public static final Logger logger = Logger.getLogger("ServerLogger");
    private final RequestManager requestManager;
    private final ResponseManager responseManager;
    private final NetworkManager networkManager;
    private final CommandManager commandManager;

    public Server(RequestManager requestManager, ResponseManager responseManager,
                  NetworkManager networkManager, CommandManager commandManager) {
        this.requestManager = requestManager;
        this.responseManager = responseManager;
        this.networkManager = networkManager;
        this.commandManager = commandManager;

    }

    @Override
    public void run() {
        while (true) {
            try (Socket client = networkManager.connectToClient()) {
                Request request = requestManager.readRequest(client);
                logger.info("Запрос от пользователя был успешно получен");
                Response response = commandManager.processRequest(request); // Обрабатываем запрос, формируем ответ

                logger.info("Запрос был успешно обработан, сформирован ответ");

                responseManager.sendToClient(response, client);
            } catch (IOException e) {
                logger.warning("Соединение с пользователем было потеряно");
            } catch (NullPointerException e) {
                logger.warning("Не удалось обработать запрос");
            }
        }
    }
}