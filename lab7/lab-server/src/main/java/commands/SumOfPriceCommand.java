package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import network.Request;
import network.Response;

import java.sql.SQLException;

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
        try {
            if (!this.getCm().getDbManager().checkUserPassword(request.getUser(), request.getPassword())) {
                return Response.wrongPassword();
            }
            float sum = this.getCm().getCollection().values().stream()
                    .reduce(0.0f, (s, t) -> s + t.getPrice(), Float::sum);
            return new Response("Сумма цен билетов: " + sum);
        } catch (SQLException e) {
            return new Response("[ERROR] Не удалось обратиться к БД");
        }
    }
}
