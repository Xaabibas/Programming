package managers;

import commands.abstraction.Command;
import network.Request;
import network.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CommandManager {
    public static final Logger logger = Logger.getLogger("CommandLogger");
    private final Map<String, Command> commands;

    public CommandManager() {
        this.commands = new HashMap<>();
    }

    public Response processRequest(Request request) {
        Response response;
        logger.info("Beginning of request processing");
        try {
            Command command = commands.get(request.getCommandName()); // Получаем команду
            logger.info("The command was got");
            response = command.execute(request); // Формируем ответ
            logger.info("The request was successfully processed and a response was generated");
        } catch (NullPointerException e) {
            logger.info("An unregistered command was transferred");
            response = new Response("[ERROR] Такая команда не зарегистрирована. Для справки введите help");
        }
        return response;
    }

    public void registerCommand(String commandName, Command command) {
        commands.put(commandName, command);
        logger.info("Command " + commandName + " was registered");
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
