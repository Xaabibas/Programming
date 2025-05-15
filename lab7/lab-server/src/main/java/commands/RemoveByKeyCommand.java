package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import managers.CommandManager;
import network.Request;
import network.Response;

import java.sql.SQLException;

public class RemoveByKeyCommand extends Command {
    public RemoveByKeyCommand(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String describe() {
        return "remove_key key - удаление элемента из коллекции по его ключу";
    }

    @Override
    public String rightFormat() {
        return "remove_key key";
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
            Long key = Long.parseLong(request.getTokens()[1]);
            if (!this.getCm().getCollection().containsKey(key)) {
                return new Response("[ERROR] В коллекции нет элемента с таким ключом");
            }
            if (this.getCm().removeByKey(key, request.getUser())){
                CommandManager.logger.info("Элемент с ключом " + request.getTokens()[1] + "был успешно удален");
                return new Response("Элемент успешно удален");
            }
            return new Response("[ERROR] Не достаточно прав для взаимодействия с данным элементом");
        } catch (NumberFormatException e) {
            return new Response("[ERROR] Key не является числом");
        } catch (SQLException e) {
            return new Response("[ERROR] Не удалось удалить элемент");
        }
    }
}
