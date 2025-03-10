package commands;

import commands.abstraction.Command;
import main.CollectionManager;

/**
 * Класс команды info - справки о коллекции
 */
public class InfoCommand extends Command {
    /**
     * @param cm - менеджер коллекции
     */
    public InfoCommand(CollectionManager cm) {
        super(cm);
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "info - вывод информации о коллекции";
    }

    /**
     * @return возвращает верный формат команды
     */
    @Override
    public String rightFormat() {
        return "info";
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
        System.out.println("Информация о коллекции:");
        System.out.println(this.getCm().getCollection().getClass());
        System.out.println("Дата инициализации: " + this.getCm().getTime()); // дата инициализации
        System.out.println("Количество элементов в коллекции: " + this.getCm().getCollection().size());
        return true;
    }
}
