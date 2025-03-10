package moduls.validator;

import moduls.TicketType;

/**
 * Класс проверки вводимого поля type
 */
public class TypeValidator implements Validator {
    /**
     * Проверка значения
     *
     * @param line - обрабатываемая строка
     * @return true, если введено корректное значение, и false, в противном случае
     */
    @Override
    public boolean validate(String line){
        try {
            if (line.isEmpty()) {
                return true;
            }

            TicketType.valueOf(line);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Значение поля введено неверно!");
        }
        return false;
    }
}
