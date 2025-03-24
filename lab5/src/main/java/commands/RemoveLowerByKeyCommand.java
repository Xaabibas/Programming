package commands;

import commands.abstraction.Command;
import main.CollectionManager;
import moduls.Ticket;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс команды remove_lower_key - удаление всех элементов коллекции, меньших заданного по ключу
 */
public class RemoveLowerByKeyCommand extends Command {
    /**
     * @param cm - менеджер коллекции
     */
    public RemoveLowerByKeyCommand(CollectionManager cm) {
        super(cm);
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "remove_lower_key - удалить из коллекции все элементы, меньшие чем заданный по ключу";
    }

    /**
     * @return возвращает верный формат команды
     */
    @Override
    public String rightFormat() {
        return "remove_lower_key key";
    }

    /**
     * Выполнение команды
     * @param args - введенная пользователем строка, разбитая на части
     * @return возвращает true при верном вводе и false - в противном случае
     */
    @Override
    public boolean execute(String... args) {
        if (args.length != 2) {
            return false;
        }
        try {
            Ticket ticket = this.getCm().getCollection().get(Long.parseLong(args[1]));
            if (ticket == null) {
                System.out.println("В коллекции отсутствует элемент с заданным ключом");
                return true;
            }
            Set<Long> removeSet = new HashSet<>();
            for (Long key : this.getCm().getCollection().keySet()) {
                if (ticket.compareTo(this.getCm().getCollection().get(key)) > 0) {
                    removeSet.add(key);
                }
            }
            for (Long key : removeSet) {
                this.getCm().getCollection().remove(key);
            }
        } catch (NumberFormatException e) {
            System.out.println("Значение key должно быть Long!");
        }
        return true;
    }
}
