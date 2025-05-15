package commands;

import commands.abstraction.Command;

public class CommandException extends RuntimeException {
    private final Command command;

    public CommandException(Command command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        return "[ERROR] Команда введена неверно. Правильный формат:\n" + command.rightFormat();
    }
}
