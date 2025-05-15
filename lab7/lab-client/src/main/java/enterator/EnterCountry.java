package enterator;

import moduls.Country;
import validator.Validator;

import java.util.Arrays;
import java.util.Scanner;

public class EnterCountry implements SimpleEnterator<Country> {
    @Override
    public Country enter(Scanner scanner, Validator validator) {
        String line;
        while (true) {
            System.out.print("Введите значение поля nationality (для справки введите list; " +
                    "для присвоения значения null введите пустую строку) > ");
            line = scanner.nextLine();
            while (line.equals("list")) {
                Arrays.stream(Country.values()).forEach(country -> System.out.println("- " + country));
                System.out.print("> ");
                line = scanner.nextLine();
            }
            if (validator.validate(line)) {
                return line.isEmpty() ? null : Country.valueOf(line);
            }
        }
    }
}
