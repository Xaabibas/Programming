package moduls.enterator;

import moduls.Person;

import java.util.Scanner;

public class EnterPerson {
    public static Person enterPerson(Scanner sc) {
        System.out.println("Ведите значения поля person (для присвоения значения null введите null):");
        if (sc.nextLine().equals("null")) {
            return null;
        }
        return new Person(EnterLocalDateTime.enterLocalDateTime(sc), EnterEyeColor.enterHairColor(sc),
                EnterHairColor.enterHairColor(sc), EnterCountry.enterCountry(sc));
    }
}
