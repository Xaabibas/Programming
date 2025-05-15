package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import managers.CommandManager;
import network.Request;
import network.Response;

public class HelpCommand extends Command {
    private final CommandManager cm;

    public HelpCommand(CollectionManager colm, CommandManager cm) {
        super(colm);
        this.cm = cm;
    }

    @Override
    public String describe() {
        return "help - вывод справки по доступным командам";
    }

    @Override
    public String rightFormat() {
        return "help";
    }

    @Override
    public Response execute(Request request) {
        if (request.getTokens().length > 1) {
            return Response.wrongCount();
        }
        StringBuilder answer = new StringBuilder();
        for (Command c : cm.getCommands().values()) {
            answer.append(c.describe()).append("\n");
        }
        return new Response(answer.toString());
    }
}
