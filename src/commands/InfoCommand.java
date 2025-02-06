package commands;

public class InfoCommand implements Command{
    @Override
    public String describe() {
        return "info - вывод информации о коллекции";
    }
}
