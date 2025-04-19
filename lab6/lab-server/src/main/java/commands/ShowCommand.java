package commands;

import commands.abstraction.Command;
import managers.CollectionManager;
import network.Request;
import network.Response;

/**
 * Класс команды show - вывода всех элементов коллекции в строковом виде
 */
public class ShowCommand extends Command {

    /**
     * @param cm - менеджер команд
     */
    public ShowCommand(CollectionManager cm) {
        super(cm);
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "show - вывод всех элементов коллекции в строковом виде";
    }

    /**
     * @return возвращает верный формат команды
     */
    @Override
    public String rightFormat() {
        return "show";
    }

    /**
     * Выполнение команды
     *
     * @param request - запрос пользователя
     * @return возвращает ответ
     */
    @Override
    public Response execute(Request request) {
        if (request.getTokens().length != 1) {
            return Response.wrongCount();
        }
        if (this.getCm().getCollection().isEmpty()) {
            return new Response("Коллекция пуста");
        }

        this.getCm().sortByName();

        StringBuilder answer = new StringBuilder();

        for (Long key : this.getCm().getCollection().keySet()) {
            answer.append(key).append(" - ").append(this.getCm().getCollection().get(key)).append("\n");
        }

        return new Response(answer.toString());
    }
}
