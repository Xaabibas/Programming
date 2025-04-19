package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import managers.CommandManager;
import network.Request;
import network.Response;
public class RemoveByKeyCommand extends Command {
    public RemoveByKeyCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public String describe() {
        return "remove_key key - удаление элемента из коллекции по его ключу";
    }
    @Override
    public String rightFormat() {
        return "remove_key key";
    }
    @Override
    public Response execute(Request request) {
        if (request.getTokens().length!=2) {
            return Response.wrongCount();
        }
        try {
            this.getCm().getCollection().remove(Long.parseLong(request.getTokens()[1]));
            CommandManager.logger.info("Элемент с ключом " + request.getTokens()[1] + "был успешно удален");
            return new Response("Элемент успешно удален");
        } catch (NumberFormatException e) {
            return new Response("[ERROR] Key не является числом");
        }
    }
}
