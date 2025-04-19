import com.sun.org.slf4j.internal.LoggerFactory;
import managers.*;
import network.Request;

import commands.*;
import network.Response;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Logger;

public final class Server {
    public static final Logger logger = Logger.getLogger("ServerLogger");

    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {

        CollectionManager collectionManager = new CollectionManager();

        try {
            logger.info("Попытка загрузить коллекцию с помощью аргумента");
            if (args.length == 0) {
                throw new IndexOutOfBoundsException();
            }
            collectionManager.getFm().registerFileByEnv(args[0]);
            collectionManager.setCollectionFromFile();

            if (collectionManager.getCollection() == null) {
                logger.severe("Невозможно извлечь коллекцию из переданного файла");
                System.exit(0);
            }

        } catch (IndexOutOfBoundsException e) {
            logger.severe("Не была передана переменная окружения");
            System.exit(0);
        }

        ServerNetworkManager networkManager = new ServerNetworkManager(46789);
        CommandManager commandManager = new CommandManager();
        RequestManager requestManager = new RequestManager();
        ResponseManager responseManager = new ResponseManager();

        commandManager.registerCommand("help", new HelpCommand(collectionManager, commandManager));
        commandManager.registerCommand("info", new InfoCommand(collectionManager));
        commandManager.registerCommand("clear", new ClearCommand(collectionManager));
        commandManager.registerCommand("count_by_type", new CountByTypeCommand(collectionManager));
        commandManager.registerCommand("insert", new InsertCommand(collectionManager));
        commandManager.registerCommand("print_ascending", new PrintAscendingCommand(collectionManager));
        commandManager.registerCommand("remove_key", new RemoveByKeyCommand(collectionManager));
        commandManager.registerCommand("remove_greater", new RemoveGreaterCommand(collectionManager));
        commandManager.registerCommand("remove_lower_key", new RemoveLowerByKeyCommand(collectionManager));
        commandManager.registerCommand("remove_lower", new RemoveLowerCommand(collectionManager));
        commandManager.registerCommand("show", new ShowCommand(collectionManager));
        commandManager.registerCommand("sum_of_price", new SumOfPriceCommand(collectionManager));
        commandManager.registerCommand("update", new UpdateCommand(collectionManager));
        commandManager.registerCommand("exit", new ExitCommand(collectionManager));

        logger.info("Успешная регистрация команд");

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