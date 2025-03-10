package moduls.validator;

import moduls.ValidationException;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class DateValidator implements Validator<LocalDateTime> {
    @Override
    public boolean validate(String line) {
        try {
            if (line.isEmpty()) {
                return true;
            }

            String[] data = line.split("\\s*;\\s*");

            if (data.length != 6) {
                throw new ValidationException("Введите корректное количество значений!");
            }

            int[] date = new int[6];

            for (int i = 0; i < 6; i++) {
                if (data[i].isEmpty()) {
                    continue;
                }
                date[i] = Integer.parseInt(data[i]);
            }

            LocalDateTime.of(date[0], date[1], date[2], date[3], date[4], date[5]);

            return true;
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Значение каждого поля должно быть int!");
        } catch (DateTimeException e) {
            System.out.println("Значения полей не являются датой!");
        }
        return false;
    }
}
