package commands;

public class RemoveLowerCommand implements Command {
    @Override
    public String describe() {
        return "remove_lower - удаление из коллекции всех элементов, меньших данного";
    }
}
