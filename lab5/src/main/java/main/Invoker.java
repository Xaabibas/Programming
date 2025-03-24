package main;

import commands.CommandException;
import commands.abstraction.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс-исполнитель
 */
public class Invoker {
    /**
     * Набор пар значений "название команды" - "экземпляр команды"
     */
    private final Map<String, Command> commands;
    /**
     * Сканер
     */
    private final Scanner scanner;

    /**
     * @param scanner - сканер
     */
    public Invoker(Scanner scanner) {
        this.scanner = scanner;
        this.commands = new HashMap<>();
    }

    /**
     * Регистрация команды
     *
     * @param commandName - название команды (верный ввод)
     * @param command     - экземпляр класса команды
     */
    public void registerCommand(String commandName, Command command) {
        commands.put(commandName, command);
    }

    /**
     * Интерактивный ражим, ожидание ввода команды от пользователя вплоть до команды exit
     */
    public void interactiveMode() {
        while (scanner.hasNext()) {
            try {
                String[] tokens = scanner.nextLine().split(" ");
                Command command = commands.get(tokens[0]);
                if (!command.execute(tokens)) {
                    throw new CommandException(command);
                } else {
                    if (command.rightFormat().equals("exit")) {
                        break;
                    }
                }
            } catch (CommandException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Команда введена неверно! Для вывода списка команд используйте help");
            }
        }
    }

    /**
     * Выполнение скрипта
     */
    public void executeScript(Scanner fileScanner) {
        while (fileScanner.hasNext()) {
            try {
                String[] tokens = fileScanner.nextLine().split(" ");
                if (tokens[0].equals("execute_script")) {
                    System.out.println("Нельзя исполнять другой файл!");
                    break;
                }
                Command command = commands.get(tokens[0]);
                if (!command.execute(tokens)) {
                    throw new CommandException(command);
                } else {
                    if (command.rightFormat().equals("exit")) {
                        break;
                    }
                }
            } catch (CommandException e) {
                System.out.println(e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Команда записана неверно! Для вывода списка команд используйте help");
            }
        }
    }

    /**
     * @return возвращает набор пар названий - команд
     */
    public Map<String, Command> getCommands() {
        return commands;
    }

    /**
     * @return возвращает сканер
     */
    public Scanner getScanner() {
        return scanner;
    }
}
