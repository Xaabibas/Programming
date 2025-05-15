package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import managers.CommandManager;
import moduls.Ticket;
import network.Request;
import network.Response;

import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;

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
        try {
            if (!this.getCm().getDbManager().checkUserPassword(request.getUser(), request.getPassword())) {
                return Response.wrongPassword();
            }
            Ticket ticket = (Ticket) request.getObj();

            Set<Long> removeSet = this.getCm().getCollection().keySet().stream().filter(
                    l -> this.getCm().getCollection().get(l).compareTo(ticket) > 0
            ).collect(Collectors.toSet());

            this.getCm().removeByKeySet(removeSet, request.getUser());

            CommandManager.logger.info("Было удалено " + removeSet.size() + " элементов");
            return new Response("Большие элементы были успешно удалены");
        } catch (SQLException e) {
            return new Response("[ERROR] Не удалось удалить большие элементы");
        }
    }
}
