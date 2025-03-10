package commands;

import commands.abstraction.Command;
import main.CollectionManager;

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
     * @param args - введенная пользователем строка, разбитая на части
     * @return возвращает true при верном вводе и false - в противном случае
     */
    @Override
    public boolean execute(String... args) {
        if (args.length != 1) {
            return false;
        }
        for (Long l : this.getCm().getCollection().keySet()) {
            System.out.println(l + " - " + this.getCm().getCollection().get(l).toString());
        }
        return true;
    }
}
