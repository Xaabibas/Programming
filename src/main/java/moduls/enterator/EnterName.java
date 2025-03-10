package moduls.enterator;

import moduls.validator.Validator;

import java.util.Scanner;

/**
 * Вспомогательный класс для ввода поля name
 */
public class EnterName implements SimpleEnterator<String> {
    /**
     * Ввод значения
     *
     * @param scanner   - сканер
     * @param validator - валидатор
     * @return возвращает введенное пользователем значение типа String
     */
    public String enter(Scanner scanner, Validator validator) {
        while (true) {
            System.out.print("Введите значение поля name > ");
            String line = scanner.nextLine();
            if (validator.validate(line)) {
                return line;
            }
        }
    }
}
