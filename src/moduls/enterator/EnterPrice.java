package moduls.enterator;

import java.util.Scanner;

public class EnterPrice {
    public static float enterPrice(Scanner sc) {
        String line;
        while (true) {
            try {
                System.out.println("Введите значение поля price (float):");
                line = sc.nextLine();
                float price = Float.parseFloat(line);
                if (price < 0) {
                    System.out.println("Значение поля price должно быть больше 0!!!");
                    continue;
                }
                return price;
            } catch (NumberFormatException e) {
                System.out.println("Значение должно быть числом!");
            }
        }
    }
}
