package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import network.Request;
import network.Response;


public class ServerExitCommand extends Command {
    public ServerExitCommand(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String describe() {
        return "exit - завершение работы приложения";
    }

    @Override
    public String rightFormat() {
        return "exit";
    }

    @Override
    public Response execute(Request request) {
        return new Response("Работа сервера завершена");
    }
}
