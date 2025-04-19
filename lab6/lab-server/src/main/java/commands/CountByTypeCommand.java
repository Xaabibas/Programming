package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import moduls.Ticket;
import moduls.TicketType;
import network.Request;
import network.Response;
public class CountByTypeCommand extends Command {
    public CountByTypeCommand(CollectionManager cm) {
        super(cm);
    }
    @Override
    public String describe() {
        return "count_by_type type - вывод количества элементов, значение поля type которых равно заданному";
    }
    @Override
    public String rightFormat() {
        return "count_by_type type";
    }
    @Override
    public Response execute(Request request) {
        if (request.getTokens().length != 2) {
            return Response.wrongCount();
        }
        String str = request.getTokens()[1];
        try {
            TicketType type = TicketType.valueOf(str);
            int cnt = 0;
            for (Ticket ticket : this.getCm().getCollection().values()) {
                try {
                    if (ticket.getType().equals(type)) {
                        cnt++;
                    }
                } catch (NullPointerException ignored) {

                }
            }
            return new Response("Количество билетов типа " + type + ": " + cnt);
        } catch (IllegalArgumentException e) {
            return new Response("Введен неверный тип Ticket");
        }
    }
}
