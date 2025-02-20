package moduls.enterator;

import java.time.LocalDateTime;
import java.util.Scanner;

public class EnterLocalDateTime {
    public static LocalDateTime enterLocalDateTime(Scanner sc) {
        String line;
        String[] data;
        int[] date = new int[6];
        while (true) {
            try {
                System.out.print("Введите данные в формате [year; month; day; hour; minute; second] (все значения int," +
                        " для присвоения значения null введите пустую строку) > ");
                line = sc.nextLine();
                if (line.isEmpty()) {
                    return null;
                }
                data = line.split("\\s*;\\s*");
                for (int i = 0; i < 6; i++) {
                    if (i >= data.length) {
                        break;
                    }
                    if (data[i].isEmpty()) {
                        continue;
                    }
                    date[i] = Integer.parseInt(data[i]);
                }
                return LocalDateTime.of(date[0], date[1], date[2], date[3], date[4], date[5]);
            } catch (NumberFormatException e) {
                System.out.println("Введите корректные значения!!!");
            }
        }
    }
}
