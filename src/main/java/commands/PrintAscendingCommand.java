package commands;

import commands.abstraction.Command;
import main.CollectionManager;
import moduls.Ticket;

import java.util.Arrays;
import java.util.Iterator;


/**
 * Класс команды print_ascending - вывода элементов коллекции по возрастанию
 */
public class PrintAscendingCommand extends Command {
    /**
     * @param cm - менеджер коллекции
     */
    public PrintAscendingCommand(CollectionManager cm) {
        super(cm);
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "print_ascending - вывод элементов коллекции по возрастанию ";
    }

    /**
     * @return возвращает верный формат команды
     */
    @Override
    public String rightFormat() {
        return "print_ascending";
    }

    /**
     * Выводит в стандартный поток вывода элементы коллекции по возрастанию
     *
     * @param args - введенная пользователем строка, разбитая на части
     * @return возвращает true, если команда была введена верно, и false в противном случае
     */
    @Override
    public boolean execute(String... args) {
        if (args.length!=1) {
            return false;
        }
        int length = this.getCm().getCollection().size();
        Ticket[] array = new Ticket[length];
        Iterator<Ticket> iterator = this.getCm().getCollection().values().iterator();
        for (int i = 0; i < length; i++) {
            array[i] = iterator.next();
        }
        Arrays.sort(array);
        for (Ticket ticket : array) {
            System.out.println(ticket.toString());
        }
        return true;
    }
}
