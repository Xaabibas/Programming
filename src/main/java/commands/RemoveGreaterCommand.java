package commands;

import commands.abstraction.Command;
import main.CollectionManager;
import moduls.Ticket;
import moduls.enterator.EnterTicket;

import java.util.Scanner;

/**
 * Класс команды remove_greater - удаление всех элементов коллекции, больших данного
 */
public class RemoveGreaterCommand extends Command {
    private final Scanner scanner;

    /**
     * @param cm      - менеджер коллекции
     * @param scanner - сканер
     */
    public RemoveGreaterCommand(CollectionManager cm, Scanner scanner) {
        super(cm);
        this.scanner = scanner;
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "remove_greater - удаление из коллекции всех элементов, больших данного";
    }

    /**
     * @return возвращает верный формат команды
     */
    @Override
    public String rightFormat() {
        return "remove_greater";
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
        Ticket ticket = new EnterTicket().enter(scanner);
        for (Long key : this.getCm().getCollection().keySet()) {
            if (ticket.compareTo(this.getCm().getCollection().get(key)) < 0) {
                this.getCm().getCollection().remove(key);
            }
        }
        return true;
    }
}
