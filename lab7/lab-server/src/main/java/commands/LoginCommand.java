package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import network.Request;
import network.Response;

import java.sql.SQLException;

public class LoginCommand extends Command {

    public LoginCommand(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String describe() {
        return "login - авторизация в системе";
    }

    @Override
    public String rightFormat() {
        return "login";
    }

    @Override
    public Response execute(Request request) {
        try {
            if (this.getCm().getDbManager().checkUserPassword(request.getUser(), request.getPassword())) {
                return new Response("Успешная авторизация");
            }
            return new Response("[ERROR] Неверный пароль");
        } catch (SQLException e) {
            return new Response("[ERROR] Неверное имя пользователя");
        }
    }
}
