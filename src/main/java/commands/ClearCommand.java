package commands;

import commands.abstraction.Command;
import main.CollectionManager;

/**
 * Класс команды clear - очистки коллекции
 */
public class ClearCommand extends Command {

    /**
     * @param cm - менеджер коллекции
     */
    public ClearCommand(CollectionManager cm) {
        super(cm);
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "clear - очистка коллекции";
    }

    /**
     * @return возвращает верный формат команды
     */
    @Override
    public String rightFormat() {
        return "clear";
    }

    /**
     * Выполнение команды
     *
     * @param args - введенная пользователем строка, разбитая на части
     * @return возвращает true, если команда была введена верно, и false, в противном случае
     */
    @Override
    public boolean execute(String... args) {
        if (args.length > 1) {
            return false;
        }
        this.getCm().getCollection().clear();
        return true;
    }
}
