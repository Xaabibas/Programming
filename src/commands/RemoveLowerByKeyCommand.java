package commands;

public class RemoveLowerByKeyCommand implements Command{
    @Override
    public String describe() {
        return "remove_lower_key - удалить из коллекции все элементы, меньшие чем заданный по ключу";
    }
}
