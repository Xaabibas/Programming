package commands.abstraction;

/**
 * Интерфейс, отвечающий за описание команд
 */
public interface Describable {
    /**
     * @return возвращает описание команды
     */
    String describe();

    /**
     * @return возвращает правильный формат команды
     */
    String rightFormat();
}
