package moduls.enterator;

import moduls.EyeColor;
import moduls.validator.Validator;

import java.util.Scanner;

/**
 * Вспомогательный класс для ввода поля eyeColor
 */
public class EnterEyeColor implements SimpleEnterator<EyeColor> {
    /**
     * Ввод значения
     *
     * @param scanner   - сканер
     * @param validator - валидатор
     * @return true, если введено корректное значение, и false, в противном случае
     */
    @Override
    public EyeColor enter(Scanner scanner, Validator validator) {
        String line;
        while (true) {
            System.out.print("Введите значение поля eyeColor (для справки введите list; " +
                    "для присвоения значения null введите пустую строку) > ");
            line = scanner.nextLine();

            while (line.equals("list")) {
                EyeColor.show();
                line = scanner.nextLine();
            }

            if (validator.validate(line)) {
                return line.isEmpty() ? null:EyeColor.valueOf(line);
            }
        }
    }
}
