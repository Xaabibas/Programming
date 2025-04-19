package validator.moduls;

import moduls.HairColor;
import validator.Validator;

public class HairValidator implements Validator {
    @Override
    public boolean validate(String line) {
        try {
            if (line.isEmpty()) {
                return true;
            }

            HairColor.valueOf(line);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] Введите корректное значение");
        }
        return false;
    }
}
