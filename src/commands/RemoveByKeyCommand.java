package commands;

public class RemoveByKeyCommand implements Command{
    @Override
    public String describe() {
        return "remove_key - удаление элемента из коллекции по его ключу";
    }
}
