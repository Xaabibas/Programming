package commands;

import commands.abstraction.Command;
import main.CollectionManager;

/**
 * Класс команды remove_key - удаление элемента коллекции по его ключу
 */
public class RemoveByKeyCommand extends Command {
    /**
     * @param cm - менеджер коллекции
     */
    public RemoveByKeyCommand(CollectionManager cm) {
        super(cm);
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "remove_key key - удаление элемента из коллекции по его ключу";
    }

    /**
     * @return возвращает верный формат команды
     */
    @Override
    public String rightFormat() {
        return "remove_key key";
    }

    /**
     * Выполнение команды
     *
     * @param args - введенная пользователем строка, разбитая на части
     * @return возвращает true при верном вводе и false - в противном случае
     */
    @Override
    public boolean execute(String... args) {
        if (args.length!=2) {
            return false;
        }
        try {
            this.getCm().getCollection().remove(Long.parseLong(args[1]));
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Ошибка! key является Long");
            return false;
        }
    }
}
