package commands;

import commands.abstraction.Command;

/**
 * Класс-исключение для ошибок ввода команд
 */
public class CommandException extends RuntimeException {
    private final Command command;

    /**
     * @param command - введенная пользователем команда
     */
    public CommandException(Command command) {
        this.command = command;
    }

    /**
     * @return возвращает сообщение об ошибке и верный формат команды
     */
    @Override
    public String getMessage() {
        return "Команда введена неверно!!! Правильный формат:\n" + command.rightFormat();
    }
}
