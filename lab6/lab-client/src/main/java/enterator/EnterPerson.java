package enterator;

import moduls.Person;
import validator.moduls.CountryValidator;
import validator.moduls.DateValidator;
import validator.moduls.EyeValidator;
import validator.moduls.HairValidator;


import java.util.Scanner;

public class EnterPerson implements ComplexEnterator<Person> {
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
