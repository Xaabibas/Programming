package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import moduls.Ticket;
import network.Request;
import network.Response;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class PrintAscendingCommand extends Command {
    public PrintAscendingCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public String describe() {
        return "print_ascending - вывод элементов коллекции по возрастанию ";
    }
    @Override
    public String rightFormat() {
        return "print_ascending";
    }
    @Override
    public Response execute(Request request) {
        if (request.getTokens().length!=1) {
            return Response.wrongCount();
        }
        this.getCm().sort();

        StringBuilder answer = new StringBuilder();
        for (Long key : this.getCm().getCollection().keySet()) {
            answer.append(key).append(this.getCm().getCollection().get(key).toString()).append("\n");
        }
        return new Response(answer.toString());
    }
}
