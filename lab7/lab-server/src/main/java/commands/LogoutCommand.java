package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import network.Request;
import network.Response;

public class LogoutCommand extends Command {

    public LogoutCommand(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String describe() {
        return "logout - разрегистрация";
    }

    @Override
    public String rightFormat() {
        return "logout";
    }

    @Override
    public Response execute(Request request) {
        return new Response("Вы вышли из системы");
    }
}
