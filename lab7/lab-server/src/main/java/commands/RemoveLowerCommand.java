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
        try {
            if (!this.getCm().getDbManager().checkUserPassword(request.getUser(), request.getPassword())) {
                return Response.wrongPassword();
            }
            Ticket ticket = (Ticket) request.getObj();
            Set<Long> removeSet = this.getCm().getCollection().keySet().stream().filter(
                    l -> this.getCm().getCollection().get(l).compareTo(ticket) < 0
            ).collect(Collectors.toSet());

            this.getCm().removeByKeySet(removeSet, request.getUser());

            CommandManager.logger.info("Было удалено " + removeSet.size() + " элементов");
            return new Response("Младшие элементы были успешно удалены");
        } catch (SQLException e) {
            return new Response("[ERROR] Не удалось удалить меньшие элементы");
        }
    }
}
