package commands;


import commands.abstraction.Command;
import main.CollectionManager;
import moduls.Ticket;
import moduls.enterator.EnterTicket;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Класс команды remove_lower - удаление из коллекции всех элементов, меньших данного
 */
public class RemoveLowerCommand extends Command {
    private final Scanner scanner;

    /**
     * @param cm      - менеджер коллекции
     * @param scanner - сканер
     */
    public RemoveLowerCommand(CollectionManager cm, Scanner scanner) {
        super(cm);
        this.scanner = scanner;
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "remove_lower - удаление из коллекции всех элементов, меньших данного";
    }

    /**
     * @return возвращает верный формат команды
     */
    @Override
    public String rightFormat() {
        return "remove_lower {element}";
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
        Set<Long> removeSet = new HashSet<>();
        for (Long key : this.getCm().getCollection().keySet()) {
            if (ticket.compareTo(this.getCm().getCollection().get(key)) > 0) {
                removeSet.add(key);
            }
        }
        for (Long key : removeSet) {
            this.getCm().getCollection().remove(key);
        }
        return true;
    }
}
