package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import moduls.TicketType;
import network.Request;
import network.Response;

import java.sql.SQLException;

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
        if (request.getTokens().length!=2) {
            return Response.wrongCount();
        }
        try {
            if (!this.getCm().getDbManager().checkUserPassword(request.getUser(), request.getPassword())) {
                return Response.wrongPassword();
            }
            TicketType type = TicketType.valueOf(request.getTokens()[1].toUpperCase());
            long cnt = this.getCm().getCollection().values().stream().filter(
                    ticket -> ticket.getType() == type
            ).count();
            return new Response("Количество билетов типа " + type + ": " + cnt);
        } catch (IllegalArgumentException e) {
            return new Response("[ERROR] Введен неверный тип Ticket");
        } catch (SQLException e) {
            return new Response("[ERROR] Не удалось подключиться к БД");
        }
    }
}
