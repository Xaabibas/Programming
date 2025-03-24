package commands;

import commands.abstraction.Command;
import main.CollectionManager;
import main.FileManager;
import main.Invoker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Класс команды execute_script - выполнение скрипта из введенного файла
 */
public class ExecuteScriptCommand extends Command {
    private final Invoker invoker;
    /**
     * @param cm - менеджер коллекции
     */
    public ExecuteScriptCommand(CollectionManager cm, Invoker invoker) {
        super(cm);
        this.invoker = invoker;
    }

    /**
     * @return возвращает описание команды
     */
    @Override
    public String describe() {
        return "execute_script - считать и выполнить скрипт из введенного файла";
    }

    /**
     * @return возвращает верный формат команды
     */
    @Override
    public String rightFormat() {
        return "execute_script file_name";
    }

    /**
     * Выполнение команды
     *
     * @param args - введенная пользователем строка, разбитая на части
     * @return возвращает true при верном вводе команды, false - в противном
     */
    @Override
    public boolean execute(String... args) {
        if (args.length < 2) {
            return false;
        }
        try {
            String path = Arrays.toString(Arrays.copyOfRange(args, 1, args.length));

            File file = new File(path.substring(1, path.length() - 1));

            Scanner scanner = new Scanner(file);

            invoker.executeScript(scanner);

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Такого файла не существует");
        }
        return true;

    }
}
