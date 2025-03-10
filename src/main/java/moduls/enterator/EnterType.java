package moduls.enterator;

import moduls.TicketType;
import moduls.validator.Validator;

import java.util.Scanner;

/**
 * Вспомогательный класс для ввода поля type
 */
public class EnterType implements SimpleEnterator<TicketType> {
    /**
     * Ввод значения
     *
     * @param scanner   - сканер
     * @param validator - валидатор
     * @return возвращает введенное пользователем значение поля type
     */
    @Override
    public TicketType enter(Scanner scanner, Validator validator) {
        String line;
        while (true) {
            System.out.print("Введите значение поля type (для справки введите list; " +
                    "для присвоения значения null введите пустую строку) > ");
            line = scanner.nextLine();

            while (line.equals("list")) {
                TicketType.show();
                line = scanner.nextLine();
            }

            if (validator.validate(line)) {
                return line.isEmpty() ? null:TicketType.valueOf(line);
            }
        }
    }
}
