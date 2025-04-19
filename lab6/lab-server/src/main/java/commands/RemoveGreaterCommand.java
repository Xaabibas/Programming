package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import managers.CommandManager;
import moduls.Ticket;
import network.Request;
import network.Response;

import java.util.HashSet;
import java.util.Set;
public class RemoveGreaterCommand extends Command {
    public RemoveGreaterCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public String describe() {
        return "remove_greater - удаление из коллекции всех элементов, больших данного";
    }
    @Override
    public String rightFormat() {
        return "remove_greater";
    }
    @Override
    public Response execute(Request request) {
        if (request.getTokens().length!=1) {
            return Response.wrongCount();
        }
        Ticket ticket = (Ticket) request.getObj();
        Set<Long> removeSet = new HashSet<>();
        for (Long key : this.getCm().getCollection().keySet()) {
            if (ticket.compareTo(this.getCm().getCollection().get(key)) < 0) {
                removeSet.add(key);
            }
        }
        for (Long key : removeSet) {
            this.getCm().getCollection().remove(key);
        }
        CommandManager.logger.info("Было удалено " + removeSet.size() + " элементов");
        return new Response("Большие элементы были успешно удалены");
    }
}
