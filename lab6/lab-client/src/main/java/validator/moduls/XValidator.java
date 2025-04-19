package validator.moduls;

import validator.ValidationException;
import validator.Validator;

public class XValidator implements Validator {
    @Override
    public boolean validate(String line) {
        try {
            if (Float.parseFloat(line) > -626) {
                return true;
            }
            throw new ValidationException("[ERROR] Значение поля x должно быть больше -626");
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Значение поля должно быть числом типа Float");
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
