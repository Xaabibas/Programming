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
            if (!this.getCm().getDbManager().checkUserPassword(request.getUser(), request.getPassword())) {
                return Response.wrongPassword();
            }
            Ticket ticket = this.getCm().getCollection().get(Long.parseLong(request.getTokens()[1]));
            if (ticket == null) {
                return new Response("В коллекции отсутствует элемент с заданным ключом");
            }
            Set<Long> removeSet = this.getCm().getCollection().keySet().stream().filter(
                    l -> this.getCm().getCollection().get(l).compareTo(ticket) < 0
            ).collect(Collectors.toSet());

            this.getCm().removeByKeySet(removeSet, request.getUser());

            CommandManager.logger.info("Было удалено " + removeSet.size() + " элементов");
            return new Response("Младшие элементы были успешно удалены");
        } catch (NumberFormatException e) {
            return new Response("[ERROR] Key не является числом");
        } catch (SQLException e) {
            return new Response("[ERROR] Не удалось удалить меньшие элементы");
        }
    }
}
