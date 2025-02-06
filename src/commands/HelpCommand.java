package commands;

public class HelpCommand implements Command{
    @Override
    public String describe() {
        return "help - вывод справки по доступным командам";
    }
}
