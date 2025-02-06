package commands;

public class CountByTypeCommand implements Command {
    @Override
    public String describe() {
        return "count_by_type - вывод количества элементов, значение поля type которых равно заданному";
    }
}
