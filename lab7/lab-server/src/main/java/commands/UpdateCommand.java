package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import managers.CommandManager;
import moduls.Ticket;
import network.Request;
import network.Response;

import java.sql.SQLException;

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
            if (!this.getCm().getDbManager().checkUserPassword(request.getUser(), request.getPassword())) {
                return Response.wrongPassword();
            }
            Long key = Long.parseLong(request.getTokens()[1]);
            if (!this.getCm().getCollection().containsKey(key)) {
                throw new IllegalArgumentException();
            }

            if (this.getCm().update(key, request.getUser(), (Ticket) request.getObj())) {
                CommandManager.logger.info("Элемент с ключом " + request.getTokens()[1] + " был успешно удален");
                return new Response("Элемент был успешно обновлен");
            }
            return new Response("[ERROR] Не достаточно прав для изменения элемента");
        } catch (NumberFormatException e) {
            return new Response("[ERROR] key является Long!");
        } catch (IllegalArgumentException e) {
            return new Response("[ERROR] Нет элемента с данным ключом");
        } catch (SQLException e) {
            return new Response("[ERROR] Не удалось обновить значение данного элемента");
        }
    }
}
