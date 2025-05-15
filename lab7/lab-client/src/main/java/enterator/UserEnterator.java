package enterator;

import java.util.Scanner;

public class UserEnterator implements ComplexEnterator<String> {
    @Override
    public String enter(Scanner scanner) {
        String name = "";
        while (name.isEmpty()) {
            System.out.print("Введите имя пользователя > ");
            name = scanner.nextLine();
        }
        return name;
    }
}
