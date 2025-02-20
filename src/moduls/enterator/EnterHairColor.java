package moduls.enterator;

import moduls.HairColor;

import java.util.Scanner;

public class EnterHairColor {
    public static HairColor enterHairColor(Scanner sc) {
        String line;
        while (true) {
            try {
                System.out.print("Введите значение поля hairColor (для справки введите help; " +
                        "для присвоения значения null введите пустую строку) > ");
                line = sc.nextLine();
                while (line.equals("help")) {
                    HairColor.show();
                    line = sc.nextLine();
                }
                if (line.isEmpty()) {
                    return null;
                }
                return HairColor.valueOf(line);
            } catch (IllegalArgumentException e) {
                System.out.println("Введите корректное значение!!!");
            }
        }
    }
}
