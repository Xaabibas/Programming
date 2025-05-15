import managers.CommandManager;
import network.Request;
import network.Response;

import java.util.Scanner;
import java.util.logging.Logger;

public class Console extends Thread{
    public static final Logger logger = Logger.getLogger("ConsoleLogger");
    private final CommandManager commandManager;
    private final Scanner scanner;

    public Console(CommandManager commandManager, Scanner scanner){
        this.commandManager = commandManager;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String commandName = line.split(" ")[0];

            Response response = commandManager.processRequest(new Request(commandName, line));

            logger.info(response.getAnswer());

            if (commandName.equals("exit")) {
                System.exit(0);
            }
        }
    }
}
