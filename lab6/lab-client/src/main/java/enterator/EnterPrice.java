package enterator;

import validator.Validator;

import java.util.Scanner;

public class EnterPrice implements SimpleEnterator<Float> {
    public Float enter(Scanner scanner, Validator validator) {
        String line;
        while (true) {
            System.out.print("Введите значение поля price > ");
            line = scanner.nextLine();
            if (validator.validate(line)) {
                return Float.parseFloat(line);
            }
        }
    }
}
