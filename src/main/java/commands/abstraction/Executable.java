package commands.abstraction;

/**
 * Интерфейс, отвечающий за исполняемые классы
 */
public interface Executable {
    /**
     * Метод выполнения команды
     * @param args - введенная пользователем строка, разбитая на части
     * @return возвращает true, если команда была введена верно, и false в противном случае
     */
    boolean execute(String... args);
}
