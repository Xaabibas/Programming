package enterator;

import moduls.HairColor;
import validator.Validator;

import java.util.Scanner;

public class EnterHairColor implements SimpleEnterator<HairColor> {
    @Override
    public HairColor enter(Scanner scanner, Validator validator) {
        String line;
        while (true) {
            System.out.print("Введите значение поля hairColor (для справки введите list; " +
                    "для присвоения значения null введите пустую строку) > ");
            line = scanner.nextLine();

            while (line.equals("list")) {
                HairColor.show();
                System.out.print("> ");
                line = scanner.nextLine();
            }

            if (validator.validate(line)) {
                return line.isEmpty() ? null:HairColor.valueOf(line);
            }
        }
    }
}
