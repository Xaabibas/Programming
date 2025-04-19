package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import network.Request;
import network.Response;
public class SaveCommand extends Command {
    public SaveCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public String describe() {
        return "save - сохранение коллекции в файл";
    }
    @Override
    public String rightFormat() {
        return "save";
    }
    @Override
    public Response execute(Request request) {
        if (request.getTokens().length!=1) {
            return Response.wrongCount();
        }
        this.getCm().getFm().writeCollection(this.getCm().getCollection());
        CollectionManager.logger.info("Коллекция успешно сохранена");
        return new Response("Коллекция успешно сохранена");
    }
}
