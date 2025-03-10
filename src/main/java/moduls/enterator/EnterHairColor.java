package moduls.enterator;

import moduls.HairColor;
import moduls.validator.Validator;

import java.util.Scanner;

/**
 * Вспомогательный класс для ввода поля hairColor
 */
public class EnterHairColor implements SimpleEnterator<HairColor> {
    /**
     * Ввод значения
     *
     * @param scanner   - сканер
     * @param validator - валидатор
     * @return возвращает введенное значение поля
     */
    @Override
    public HairColor enter(Scanner scanner, Validator validator) {
        String line;
        while (true) {
            System.out.print("Введите значение поля hairColor (для справки введите list; " +
                    "для присвоения значения null введите пустую строку) > ");
            line = scanner.nextLine();

            while (line.equals("list")) {
                HairColor.show();
                line = scanner.nextLine();
            }

            if (validator.validate(line)) {
                return line.isEmpty() ? null:HairColor.valueOf(line);
            }
        }
    }
}
