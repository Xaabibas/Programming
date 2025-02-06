package commands;

public class RemoveGreaterCommand implements Command {
    @Override
    public String describe() {
        return "remove_greater - удаление из коллекции всех элементов, больших данного";
    }
}
