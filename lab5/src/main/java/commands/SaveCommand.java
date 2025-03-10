package commands;

import commands.abstraction.Command;
import main.CollectionManager;

/**
 * Класс команды save - сохранение коллекции в файл
 */
public class SaveCommand extends Command {
    /**
     * @param cm - менеджер команд
     */
    public SaveCommand(CollectionManager cm) {
        super(cm);
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "save - сохранение коллекции в файл";
    }

    /**
     * @return возвращает верный формат команды
     */
    @Override
    public String rightFormat() {
        return "save";
    }

    /**
     * Выполнение команды
     *
     * @param args - введенная пользователем строка, разбитая на части
     * @return возвращает true при верном вводе и false - в противном случае
     */
    @Override
    public boolean execute(String... args) {
        if (args.length!=1) {
            return false;
        }
        this.getCm().getFm().writeCollection(this.getCm().getCollection());
        return true;
    }
}
