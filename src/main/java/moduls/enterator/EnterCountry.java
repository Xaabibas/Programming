package moduls.enterator;

import moduls.Country;
import moduls.validator.Validator;

import java.util.Scanner;

/**
 * Вспомогательный класс для ввода поля nationality
 */
public class EnterCountry implements SimpleEnterator<Country> {
    /**
     * Ввод значения
     *
     * @param scanner - сканер
     * @param validator - валидатор
     * @return возвращает введенное пользователем значение поля nationality
     */
    @Override
    public Country enter(Scanner scanner, Validator validator) {
        String line;
        while (true) {
            System.out.print("Введите значение поля nationality (для справки введите list; " +
                    "для присвоения значения null введите пустую строку) > ");
            line = scanner.nextLine();
            while (line.equals("list")) {
                Country.show();
                line = scanner.nextLine();
            }
            if (validator.validate(line)) {
                return line.isEmpty() ? null : Country.valueOf(line);
            }
        }
    }
}
