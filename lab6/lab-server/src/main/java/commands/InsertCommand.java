package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import moduls.Ticket;
import network.Request;
import network.Response;

import java.util.Scanner;
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
            Long key = Long.parseLong(request.getTokens()[1]);
            if (this.getCm().getCollection().containsKey(key)) {
                throw new IllegalArgumentException();
            }
            Ticket ticket = (Ticket) request.getObj();
            ticket.setId();
            this.getCm().getCollection().put(key, ticket);
        } catch (NumberFormatException e) {
            return new Response("[ERROR] Key не является числом");
        } catch (IllegalArgumentException e) {
            return  new Response("[ERROR] Данное значение уже является ключом");
        }
        CollectionManager.logger.info("В коллекцию был добавлен новый элемент");
        return new Response("Элемент успешно добавлен в коллекцию");
    }
}
