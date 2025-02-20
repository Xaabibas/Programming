package moduls.enterator;

import moduls.Country;

import java.util.Scanner;

public class EnterCountry {
    public static Country enterCountry(Scanner sc) {
        String line;
        while (true) {
            try {
                System.out.print("Введите значение поля nationality (для справки введите help; " +
                        "для присвоения значения null введите пустую строку) > ");
                line = sc.nextLine();
                while (line.equals("help")) {
                    Country.show();
                    line = sc.nextLine();
                }
                if (line.isEmpty()) {
                    return null;
                }
                return Country.valueOf(line);
            } catch (IllegalArgumentException e) {
                System.out.println("Введите корректное значение!!!");
            }
        }
    }
}
