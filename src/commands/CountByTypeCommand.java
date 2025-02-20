package commands;

import main.CollectionManager;

import java.util.NoSuchElementException;

public class CountByTypeCommand extends Command {
    public CountByTypeCommand(CollectionManager cm) {
        super(cm);
    }

    @Override
    public String describe() {
        return "count_by_type - вывод количества элементов, значение поля type которых равно заданному";
    }

    @Override
    public String rightFormat() {
        return "count_by_type type";
    }

    @Override
    public boolean execute(String... args) {
        if (args.length != 2) { // Некорректный тип
            return false;
        }
        try {
            this.getCm().countByType(args[1]);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
