package commands;

public class InsertCommand implements Command {
    @Override
    public String describe() {
        return "insert - добавить новый элемент по заданному ключу";
    }
}
