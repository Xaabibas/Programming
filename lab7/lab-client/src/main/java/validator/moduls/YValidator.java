package validator.moduls;

import validator.Validator;

public class YValidator implements Validator {
    @Override
    public boolean validate(String line) {
        try {
            Long.parseLong(line);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Значение должно быть числом типа Long");
        }
        return false;
    }
}
