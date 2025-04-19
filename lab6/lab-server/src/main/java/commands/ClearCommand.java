package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import managers.CommandManager;
import network.Request;
import network.Response;
public class ClearCommand extends Command {
    public ClearCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public String describe() {
        return "clear - очистка коллекции";
    }
    @Override
    public String rightFormat() {
        return "clear";
    }
    @Override
    public Response execute(Request request) {
        if (request.getTokens().length > 1) {
            return Response.wrongCount();
        }
        this.getCm().getCollection().clear();
        CommandManager.logger.info("Коллекция успешно очищена");
        return new Response("Коллекция успешно очищена");
    }
}
