import commands.*;
import managers.*;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    public static final Logger logger = Logger.getLogger("MainLogger");

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

        NetworkManager networkManager = new NetworkManager(46789);
        CommandManager clientCommandManager = new CommandManager();
        RequestManager requestManager = new RequestManager();
        ResponseManager responseManager = new ResponseManager();

        clientCommandManager.registerCommand("help", new HelpCommand(collectionManager, clientCommandManager));
        clientCommandManager.registerCommand("info", new InfoCommand(collectionManager));
        clientCommandManager.registerCommand("clear", new ClearCommand(collectionManager));
        clientCommandManager.registerCommand("count_by_type", new CountByTypeCommand(collectionManager));
        clientCommandManager.registerCommand("insert", new InsertCommand(collectionManager));
        clientCommandManager.registerCommand("print_ascending", new PrintAscendingCommand(collectionManager));
        clientCommandManager.registerCommand("remove_key", new RemoveByKeyCommand(collectionManager));
        clientCommandManager.registerCommand("remove_greater", new RemoveGreaterCommand(collectionManager));
        clientCommandManager.registerCommand("remove_lower_key", new RemoveLowerByKeyCommand(collectionManager));
        clientCommandManager.registerCommand("remove_lower", new RemoveLowerCommand(collectionManager));
        clientCommandManager.registerCommand("show", new ShowCommand(collectionManager));
        clientCommandManager.registerCommand("sum_of_price", new SumOfPriceCommand(collectionManager));
        clientCommandManager.registerCommand("update", new UpdateCommand(collectionManager));
        clientCommandManager.registerCommand("exit", new ExitCommand(collectionManager));

        CommandManager serverCommandManager = new CommandManager();

        serverCommandManager.registerCommand("save", new SaveCommand(collectionManager));
        serverCommandManager.registerCommand("exit", new ServerExitCommand(collectionManager));
        serverCommandManager.registerCommand("help", new HelpCommand(collectionManager, serverCommandManager));

        logger.info("Успешная регистрация команд");

        Server server = new Server(requestManager, responseManager, networkManager, clientCommandManager);

        Console console = new Console(serverCommandManager, new Scanner(System.in));

        console.start();

        server.start();
    }
}
