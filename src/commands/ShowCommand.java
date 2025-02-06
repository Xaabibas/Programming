package commands;

public class ShowCommand implements Command{
    @Override
    public String describe() {
        return "show - вывод всех элементов коллекции в строковом виде";
    }
}
