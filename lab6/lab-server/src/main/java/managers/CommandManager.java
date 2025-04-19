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
        logger.info("Начало обработки запроса");
        try {
            Command command = commands.get(request.getCommandName()); // Получаем команду

            return command.execute(request); // Формируем ответ
        } catch (NullPointerException e) {
            logger.info("Была передана не зарегистрированная команда");
            return new Response("[ERROR] Такой команды не существует, для справки введите help");
        }
    }

    public void registerCommand(String commandName, Command command) {
        commands.put(commandName, command);
        logger.info("Регистрация команды " + commandName);
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}
