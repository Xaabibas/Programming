package moduls.enterator;

import moduls.validator.Validator;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Вспомогательный класс для ввода поля birthday
 */
public class EnterLocalDateTime implements SimpleEnterator<LocalDateTime> {
    /**
     * Ввод значения
     *
     * @param scanner   - сканер
     * @param validator - валидатор
     * @return возвращает введенное пользователем значение типа LocalDateTime
     */
    public LocalDateTime enter(Scanner scanner, Validator validator) {
        String line;
        String[] data;
        int[] date = new int[6];
        while (true) {
            System.out.print("Введите данные в формате [year; month; day; hour; minute; second] (все значения int," +
                    " для присвоения значения null введите пустую строку) > ");
            line = scanner.nextLine();

            if (!validator.validate(line)) {
                continue;
            }
            if (line.isEmpty()) {
                return null;
            }

            data = line.split("\\s*;\\s*");
            for (int i = 0; i < 6; i++) {
                if (data[i].isEmpty()) {
                    continue;
                }
                date[i] = Integer.parseInt(data[i]);
            }
            return LocalDateTime.of(date[0], date[1], date[2], date[3], date[4], date[5]);
        }
    }
}
