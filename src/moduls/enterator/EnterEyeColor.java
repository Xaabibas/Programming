package moduls.enterator;

import moduls.EyeColor;

import java.util.Scanner;

public class EnterEyeColor {
    public static EyeColor enterHairColor(Scanner sc) {
        String line;
        while (true) {
            try {
                System.out.print("Введите значение поля eyeColor (для справки введите help; " +
                        "для присвоения значения null введите пустую строку) > ");
                line = sc.nextLine();
                while (line.equals("help")) {
                    EyeColor.show();
                    line = sc.nextLine();
                }
                if (line.isEmpty()) {
                    return null;
                }
                return EyeColor.valueOf(line);
            } catch (IllegalArgumentException e) {
                System.out.println("Введите корректное значение!!!");
            }
        }
    }
}
