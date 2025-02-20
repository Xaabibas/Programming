package moduls.enterator;

import moduls.TicketType;

import java.util.Scanner;

public class EnterType {
    public static TicketType enterType(Scanner sc) {
        String line;
        while (true) {
            try {
                System.out.print("Введите значение поля type (для справки введите help; " +
                        "для присвоения значения null введите пустую строку) > ");
                line = sc.nextLine();
                while (line.equals("help")) {
                    TicketType.show();
                    line = sc.nextLine();
                }
                if (line.isEmpty()) {
                    return null;
                }
                return TicketType.valueOf(line);
            } catch (IllegalArgumentException e) {
                System.out.println("Введите корректное значение!!!");
            }
        }
    }
}
