package commands;

import commands.abstraction.Command;
import main.CollectionManager;
import moduls.enterator.EnterTicket;

import java.util.Scanner;

/**
 * Класс команды update - обновления значения элемента коллекции по id
 */
public class UpdateCommand extends Command {
    private final Scanner scanner;

    /**
     * @param cm      - менеджер коллекции
     * @param scanner - сканер
     */
    public UpdateCommand(CollectionManager cm, Scanner scanner) {
        super(cm);
        this.scanner = scanner;
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "update id {element} - обновить значение элемента коллекции, id которого равен заданному";
    }

    /**
     * @return возвращает верный формат команды
     */
    @Override
    public String rightFormat() {
        return "update id";
    }

    /**
     * Выполнение команды
     *
     * @param args - введенная пользователем строка, разбитая на части
     * @return возвращает true при верном вводе и false - в противном случае
     */
    @Override
    public boolean execute(String... args) {
        if (args.length!=2) {
            return false;
        }
        try {
            Long key = Long.parseLong(args[1]);
            if (!this.getCm().getCollection().containsKey(key)) {
                throw new IllegalArgumentException();
            }
            this.getCm().getCollection().replace(key, new EnterTicket().enter(scanner));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка! key является Long!");
        } catch (IllegalArgumentException e) {
            System.out.println("Данное значение не является ключом");
        }
        return true;
    }
}
