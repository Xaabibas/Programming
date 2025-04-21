package enterator;

import moduls.TicketType;
import validator.Validator;

import java.util.Arrays;
import java.util.Scanner;

public class EnterType implements SimpleEnterator<TicketType> {
    @Override
    public TicketType enter(Scanner scanner, Validator validator) {
        String line;
        while (true) {
            System.out.print("Введите значение поля type (для справки введите list; " +
                    "для присвоения значения null введите пустую строку) > ");
            line = scanner.nextLine();

            while (line.equals("list")) {
                Arrays.stream(TicketType.values()).forEach(type -> System.out.println("- " + type));
                System.out.print("> ");
                line = scanner.nextLine();
            }

            if (validator.validate(line)) {
                return line.isEmpty() ? null:TicketType.valueOf(line);
            }
        }
    }
}
