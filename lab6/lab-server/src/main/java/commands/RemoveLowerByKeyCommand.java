package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import managers.CommandManager;
import moduls.Ticket;
import network.Request;
import network.Response;

import java.util.HashSet;
import java.util.Set;
public class RemoveLowerByKeyCommand extends Command {
    public RemoveLowerByKeyCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public String describe() {
        return "remove_lower_key - удалить из коллекции все элементы, меньшие чем заданный по ключу";
    }
    @Override
    public String rightFormat() {
        return "remove_lower_key key";
    }
    @Override
    public Response execute(Request request) {
        if (request.getTokens().length != 2) {
            return Response.wrongCount();
        }
        try {
            Ticket ticket = this.getCm().getCollection().get(Long.parseLong(request.getTokens()[1]));
            if (ticket == null) {
                return new Response("В коллекции отсутствует элемент с заданным ключом");
            }
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
        } catch (NumberFormatException e) {
            return new Response("[ERROR] Key не является числом");
        }
    }
}
