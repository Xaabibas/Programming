package moduls.enterator;

import moduls.HairColor;
import moduls.Person;
import moduls.validator.*;

import java.util.Scanner;

/**
 * Вспомогательный класс для ввода поля person
 */
public class EnterPerson implements ComplexEnterator<Person> {
    /**
     * @param scanner - сканер
     * @return возвращает введенное пользователем значение типа Person
     */
    public Person enter(Scanner scanner) {
        System.out.print("Ведите значения поля person (для присвоения значения null введите null):");
        if (scanner.nextLine().equals("null")) {
            return null;
        }
        return new Person(new EnterLocalDateTime().enter(scanner, new DateValidator()),
                new EnterEyeColor().enter(scanner, new EyeValidator()),
                new EnterHairColor().enter(scanner, new HairValidator()),
                new EnterCountry().enter(scanner, new CountryValidator()));
    }
}
