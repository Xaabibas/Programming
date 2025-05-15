package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import managers.DataBaseManager;
import network.Request;
import network.Response;

import java.sql.SQLException;

public class RegisterCommand extends Command {

    public RegisterCommand(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String describe() {
        return "register - регистрация в системе";
    }

    @Override
    public String rightFormat() {
        return "register";
    }

    @Override
    public Response execute(Request request) {
        try {
            this.getCm().getDbManager().register(request.getUser(), request.getPassword());
            return new Response("Успешная регистрация");
        } catch (SQLException e) {
            return new Response("[ERROR] Пользователь с таким именем уже зарегистрирован");
        }
    }
}
