package moduls.enterator;

import moduls.Coordinates;

import java.util.Scanner;

public class EnterCoordinates {
    public static Coordinates enterCoordinates(Scanner sc) {
        System.out.println("Введите значение поля coordinates");
        while (true) {
            try {
                System.out.print("Введите значения полей x(float) и y(long) в формате [x; y] > ");
                String[] data = sc.nextLine().split("\\s*;\\s*");
                if (data.length!=2) {
                    throw new Exception("Ввести необходимо значение 2 полей!!!");
                }
                return new Coordinates(Float.parseFloat(data[0]), Long.parseLong(data[1]));
            } catch (NumberFormatException e) {
                System.out.println("Введите корректные значения!!!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
