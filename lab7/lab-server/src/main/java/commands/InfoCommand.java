package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import network.Request;
import network.Response;

import java.sql.SQLException;

public class InfoCommand extends Command {
    public InfoCommand(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String describe() {
        return "info - вывод информации о коллекции";
    }

    @Override
    public String rightFormat() {
        return "info";
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

            String answer = "--Информация о коллекции--\n" +
                    this.getCm().getCollection().getClass() + "\n" +
                    "Дата инициализации: " + this.getCm().getTime() + "\n" +
                    "Количество элементов в коллекции: " + this.getCm().getCollection().size();

            return new Response(answer);
        } catch (SQLException e) {
            return new Response("[ERROR] Не удалось обратиться к БД");
        }
    }
}
