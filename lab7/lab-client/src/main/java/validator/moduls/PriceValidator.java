package validator.moduls;

import validator.ValidationException;
import validator.Validator;

public class PriceValidator implements Validator {
    @Override
    public boolean validate(String line) {
        try {
            if (Float.parseFloat(line) > 0) {
                return true;
            }
            throw new ValidationException("[ERROR] Число должно быть больше 0");
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Значение должно быть числом");
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
