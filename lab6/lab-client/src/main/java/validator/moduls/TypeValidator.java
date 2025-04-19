package validator.moduls;

import moduls.TicketType;
import validator.Validator;

public class TypeValidator implements Validator {
    @Override
    public boolean validate(String line){
        try {
            if (line.isEmpty()) {
                return true;
            }

            TicketType.valueOf(line);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] Значение поля введено неверно");
        }
        return false;
    }
}
