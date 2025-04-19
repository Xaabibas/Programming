package enterator;

import moduls.EyeColor;
import validator.Validator;

import java.util.Scanner;

public class EnterEyeColor implements SimpleEnterator<EyeColor> {
    @Override
    public EyeColor enter(Scanner scanner, Validator validator) {
        String line;
        while (true) {
            System.out.print("Введите значение поля eyeColor (для справки введите list; " +
                    "для присвоения значения null введите пустую строку) > ");
            line = scanner.nextLine();

            while (line.equals("list")) {
                EyeColor.show();
                System.out.print("> ");
                line = scanner.nextLine();
            }

            if (validator.validate(line)) {
                return line.isEmpty() ? null:EyeColor.valueOf(line);
            }
        }
    }
}
