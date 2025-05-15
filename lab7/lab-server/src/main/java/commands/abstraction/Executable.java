package commands.abstraction;

import network.Request;
import network.Response;

/**
 * Интерфейс, отвечающий за исполняемые классы
 */
public interface Executable {
    /**
     * @param request - запрос пользователя
     * @return возвращает ответ
     */
    Response execute(Request request);
}
