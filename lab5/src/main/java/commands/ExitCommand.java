package commands;

import commands.abstraction.Command;
import main.CollectionManager;

/**
 * Класс команды exit - завершение программы
 */
public class ExitCommand extends Command {
    /**
     * @param cm - менеджер коллекции
     */
    public ExitCommand(CollectionManager cm) {
        super(cm);
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "exit - завершение программы";
    }

    /**
     * @return возвращает верный формат команды
     */
    @Override
    public String rightFormat() {
        return "exit";
    }

    /**
     * Выполнение команды
     *
     * @param args - введенная пользователем строка, разбитая на части
     * @return возвращает true при верном вводе и false - в противном случае
     */
    @Override
    public boolean execute(String... args) {
        return args.length==1;
    }
}
