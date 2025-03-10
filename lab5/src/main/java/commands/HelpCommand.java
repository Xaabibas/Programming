package commands;

import commands.abstraction.Command;
import main.CollectionManager;
import main.Invoker;

/**
 * Класс команды help - вывод справки
 */
public class HelpCommand extends Command {
    private final Invoker invoker;

    /**
     * @param cm      - менеджер коллекции
     * @param invoker - исполнитель, содержит список зарегистрированных команд
     */
    public HelpCommand(CollectionManager cm, Invoker invoker) {
        super(cm);
        this.invoker = invoker;
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "help - вывод справки по доступным командам";
    }

    /**
     * @return возвращает верный формат команды
     */
    @Override
    public String rightFormat() {
        return "help";
    }

    /**
     * Выполнение команды
     *
     * @param args - введенная пользователем строка, разбитая на части
     * @return возвращает true при верном вводе и false - в противном случае
     */
    @Override
    public boolean execute(String... args) {
        if (args.length > 1) {
            return false;
        }
        for (Command c : invoker.getCommands().values()) {
            System.out.println(c.describe());
        }
        return true;
    }
}
