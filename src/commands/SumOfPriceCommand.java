package commands;

public class SumOfPriceCommand implements Command {
    @Override
    public String describe() {
        return "sum_of_price - вывести сумму значений поля price всех элементов в коллекции";
    }
}
