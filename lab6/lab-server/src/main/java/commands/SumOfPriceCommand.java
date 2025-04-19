package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import moduls.Ticket;
import network.Request;
import network.Response;
public class SumOfPriceCommand extends Command {
    public SumOfPriceCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public String describe() {
        return "sum_of_price - вывести сумму значений поля price всех элементов в коллекции";
    }
    @Override
    public String rightFormat() {
        return "sum_of_price";
    }
    @Override
    public Response execute(Request request) {
        if (request.getTokens().length!=1) {
            return Response.wrongCount();
        }
        float sum = 0;
        for (Ticket ticket : this.getCm().getCollection().values()) {
            sum = sum + ticket.getPrice();
        }
        return new Response("Сумма цен билетов: " + sum);
    }
}
