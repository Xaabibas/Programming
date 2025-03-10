package moduls.enterator;

import moduls.Coordinates;
import moduls.ValidationException;
import moduls.validator.XValidator;
import moduls.validator.YValidator;

import java.util.Scanner;

/**
 * Вспомогательный класс для ввода воля coordinates
 */
public class EnterCoordinates implements ComplexEnterator<Coordinates> {
    /**
     * @param scanner - сканер
     * @return возвращает введенное пользователем значение типа Coordinates
     */
    public Coordinates enter(Scanner scanner) {
        System.out.println("Введите значение поля coordinates");
        while (true) {
            try {
                System.out.print("Введите значения полей x(float) и y(long) в формате [x; y] > ");
                String[] data = scanner.nextLine().split("\\s*;\\s*");
                if (data.length!=2) {
                    throw new ValidationException("Ввести необходимо 2 значения полей!!!");
                }
                if (new XValidator().validate(data[0]) && new YValidator().validate(data[1])){
                    return new Coordinates(Float.parseFloat(data[0]), Long.parseLong(data[1]));
                }
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
