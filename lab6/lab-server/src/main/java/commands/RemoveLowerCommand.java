package commands;


import commands.abstraction.Command;
import managers.CollectionManager;
import managers.CommandManager;
import moduls.Ticket;
import network.Request;
import network.Response;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class RemoveLowerCommand extends Command {
    public RemoveLowerCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public String describe() {
        return "remove_lower - удаление из коллекции всех элементов, меньших данного";
    }
    @Override
    public String rightFormat() {
        return "remove_lower {element}";
    }
    @Override
    public Response execute(Request request) {
        if (request.getTokens().length!=1) {
            return Response.wrongCount();
        }
        Ticket ticket = (Ticket) request.getObj();
        Set<Long> removeSet = new HashSet<>();
        for (Long key : this.getCm().getCollection().keySet()) {
            if (ticket.compareTo(this.getCm().getCollection().get(key)) > 0) {
                removeSet.add(key);
            }
        }
        for (Long key : removeSet) {
            this.getCm().getCollection().remove(key);
        }
        CommandManager.logger.info("Было удалено " + removeSet.size() + " элементов");
        return new Response("Младшие элементы были успешно удалены");
    }
}
