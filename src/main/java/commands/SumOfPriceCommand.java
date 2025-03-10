package commands;

import commands.abstraction.Command;
import main.CollectionManager;
import moduls.Ticket;

/**
 * Класс команды sum_of_price - вывода суммы значений поля price всех элементов коллекции
 */
public class SumOfPriceCommand extends Command {
    /**
     * @param cm - менеджер коллекции
     */
    public SumOfPriceCommand(CollectionManager cm) {
        super(cm);
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "sum_of_price - вывести сумму значений поля price всех элементов в коллекции";
    }

    /**
     * @return возвращает верный формат команды
     */
    @Override
    public String rightFormat() {
        return "sum_of_price";
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
        float sum = 0;
        for (Ticket ticket : this.getCm().getCollection().values()) {
            sum = sum + ticket.getPrice();
        }
        System.out.println("Сумма цен билетов: " + sum);
        return true;
    }
}
