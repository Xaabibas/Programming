package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import moduls.Ticket;
import network.Request;
import network.Response;

import java.sql.SQLException;

public class InsertCommand extends Command {
    public InsertCommand(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String describe() {
        return "insert key {element} - добавить новый элемент по заданному ключу";
    }

    @Override
    public String rightFormat() {
        return "insert key";
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
            if (this.getCm().getCollection().containsKey(key)) {
                throw new IllegalArgumentException();
            }
            this.getCm().insert(key, (Ticket) request.getObj(), request.getUser());
            CollectionManager.logger.info("A new item has been added to the collection");
            return new Response("Элемент был успешно добавлен в коллекцию");
        } catch (NumberFormatException e) {
            return new Response("[ERROR] Key не является числом");
        } catch (IllegalArgumentException e) {
            return new Response("[ERROR] Данное значение уже является ключом");
        } catch (SQLException e) {
            return  new Response("[ERROR] Не удалось добавить элемент в коллекцию");
        }
    }
}
