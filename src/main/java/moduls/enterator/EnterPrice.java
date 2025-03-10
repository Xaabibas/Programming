package moduls.enterator;

import moduls.validator.Validator;

import java.util.Scanner;

/**
 * Вспомогательный класс для ввода поля price
 */
public class EnterPrice implements SimpleEnterator<Float> {
    /**
     * @param scanner   - сканер
     * @param validator - валидатор
     * @return возвращает введенное пользователем значение типа float
     */
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
