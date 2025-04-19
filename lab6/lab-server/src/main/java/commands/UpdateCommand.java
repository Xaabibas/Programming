package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import managers.CommandManager;
import moduls.Ticket;
import network.Request;
import network.Response;
public class UpdateCommand extends Command {
    public UpdateCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public String describe() {
        return "update id {element} - обновить значение элемента коллекции, id которого равен заданному";
    }
    @Override
    public String rightFormat() {
        return "update id";
    }
    @Override
    public Response execute(Request request) {
        if (request.getTokens().length!=2) {
            return Response.wrongCount();
        }
        try {
            Long key = Long.parseLong(request.getTokens()[1]);
            if (!this.getCm().getCollection().containsKey(key)) {
                throw new IllegalArgumentException();
            }
            Ticket ticket = (Ticket) request.getObj();
            ticket.setId();
            this.getCm().getCollection().replace(key, ticket);
            CommandManager.logger.info("Элемент с ключом " + request.getTokens()[1] + " был успешно удален");
            return new Response("Элемент был успешно обновлен");
        } catch (NumberFormatException e) {
            return new Response("Ошибка! key является Long!");
        } catch (IllegalArgumentException e) {
            return new Response("Нет элемента с данным ключом");
        }
    }
}
