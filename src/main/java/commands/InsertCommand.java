package commands;

import commands.abstraction.Command;
import main.CollectionManager;
import moduls.enterator.EnterTicket;

import java.util.Scanner;

/**
 * Класс команды insert - добавление в коллекцию нового элемента
 */
public class InsertCommand extends Command {
    private final Scanner scanner;

    /**
     * @param cm      - менеджер коллекции
     * @param scanner - сканер для получения пользовательского ввода
     */
    public InsertCommand(CollectionManager cm, Scanner scanner) {
        super(cm);
        this.scanner = scanner;
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "insert key {element} - добавить новый элемент по заданному ключу";
    }

    /**
     * @return возвращает верный формат команды
     */
    @Override
    public String rightFormat() {
        return "insert key";
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
            if (this.getCm().getCollection().containsKey(key)) {
                throw new IllegalArgumentException();
            }
            this.getCm().getCollection().put(key, new EnterTicket().enter(scanner));
        } catch (NumberFormatException e) {
            System.out.println("Ошибка! key является Long!");
        } catch (IllegalArgumentException e) {
            System.out.println("Данное значение уже является ключом");
        }
        return true;
    }
}
