package validator.moduls;

import validator.Validator;

public class NameValidator implements Validator {
    @Override
    public boolean validate(String line) {
        if (line.isEmpty()) {
            System.out.println("[ERROR] Значение поля name не может быть null или пустой строкой");
        }
        return !line.isEmpty();
    }
}
