package moduls.enterator;

import java.util.Scanner;

public class EnterName {
    public static String enterName(Scanner sc) {
        while (true) {
            System.out.print("Введите значение поля name > ");
            String line = sc.nextLine();
            if (line.isEmpty()) {
                System.out.println("Поле name не может быть пустой строкой!!!");
                continue;
            }
            return line;
        }
    }
}
