package main;

import commands.*;

import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Main класс
 */
public class Main {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        CollectionManager cm = new CollectionManager();

        boolean exit = false;

        String line;
        while (true) {
            System.out.print("Введите имя переменной (для выхода введите exit) > ");
            line = scanner.nextLine();
            if (line.equals("exit")) {
                exit = true;
                break;
            }
            if (cm.getFm().registerFileByEnv(line)) {
                cm.setCollection(cm.getFm().readCollection());
                if ((cm.getCollection() == null)) {
                    System.out.println("Данный файл не создал коллекцию");
                    System.out.print("Создать новую (Тогда в данный файл будет сохраняться коллекция)? yes/no > ");
                    line = scanner.nextLine();
                    if (line.equals("yes")) {
                        cm.setCollection(new LinkedHashMap<>());
                        cm.getFm().clearFile();
                    } else {
                        continue;
                    }
                }
                System.out.println("--Коллекция успешно загружена--");
                break;
            }
        }

        if (!exit) {
            Invoker invoker = new Invoker(scanner);
            invoker.registerCommand("clear", new ClearCommand(cm));
            invoker.registerCommand("help", new HelpCommand(cm, invoker));
            invoker.registerCommand("info", new InfoCommand(cm));
            invoker.registerCommand("show", new ShowCommand(cm));
            invoker.registerCommand("insert", new InsertCommand(cm, invoker.getScanner()));
            invoker.registerCommand("update", new UpdateCommand(cm, invoker.getScanner()));
            invoker.registerCommand("remove_key", new RemoveByKeyCommand(cm));
            invoker.registerCommand("clear", new ClearCommand(cm));
            invoker.registerCommand("save", new SaveCommand(cm));
            invoker.registerCommand("execute_script", new ExecuteScriptCommand(cm, invoker));
            invoker.registerCommand("exit", new ExitCommand(cm));
            invoker.registerCommand("remove_greater", new RemoveGreaterCommand(cm, invoker.getScanner()));
            invoker.registerCommand("remove_lower", new RemoveLowerCommand(cm, invoker.getScanner()));
            invoker.registerCommand("remove_lower_key", new RemoveLowerByKeyCommand(cm));
            invoker.registerCommand("sum_of_price", new SumOfPriceCommand(cm));
            invoker.registerCommand("count_by_type", new CountByTypeCommand(cm));
            invoker.registerCommand("print_ascending", new PrintAscendingCommand(cm));

            invoker.interactiveMode();

        }

    }
}
