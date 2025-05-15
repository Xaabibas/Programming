package validator.moduls;

import moduls.EyeColor;
import validator.Validator;

public class EyeValidator implements Validator {
    @Override
    public boolean validate(String line) {
        try {
            if (line.isEmpty()) {
                return true;
            }

            EyeColor.valueOf(line);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] Введите корректное значение");
        }
        return false;
    }
}
