package commands;

import commands.abstraction.Command;
import main.CollectionManager;
import moduls.Ticket;
import moduls.TicketType;

/**
 * Класс команды count_by_type type - подсчета количества элементов коллекции, значение поля type которых равно заданному
 */
public class CountByTypeCommand extends Command {
    /**
     * @param cm - менеджер коллекции
     */
    public CountByTypeCommand(CollectionManager cm) {
        super(cm);
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "count_by_type type - вывод количества элементов, значение поля type которых равно заданному";
    }

    /**
     * @return возвращает правильный формат команды
     */
    @Override
    public String rightFormat() {
        return "count_by_type type";
    }

    /**
     * Выполнение команды
     *
     * @param args - введенная пользователем строка, разбитая на части
     * @return возвращает true, если команда была введена верно, и false в противном случае
     */
    @Override
    public boolean execute(String... args) {
        if (args.length!=2) {
            return false;
        }
        try {
            TicketType type = TicketType.valueOf(args[1]);
            int cnt = 0;
            for (Ticket ticket : this.getCm().getCollection().values()) {
                try {
                    if (ticket.getType().equals(type)) {
                        cnt++;
                    }
                } catch (NullPointerException ignored) {

                }
            }
            System.out.println("Количество билетов типа " + type + ": " + cnt);
        } catch (IllegalArgumentException e) {
            System.out.println("Введен неверный тип Ticket");
        }
        return true;
    }
}
