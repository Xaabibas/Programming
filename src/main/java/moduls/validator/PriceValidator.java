package moduls.validator;

import moduls.ValidationException;

/**
 * Класс проверки корректности введенного поля price
 */
public class PriceValidator implements Validator<Float> {
    /**
     * Проверка значения
     *
     * @param line - обрабатываемая строка
     * @return true, если введено корректное значение, и false, в противном случае
     */
    @Override
    public boolean validate(String line) {
        try {
            if (Float.parseFloat(line) > 0) {
                return true;
            }
            throw new ValidationException("Число должно быть больше 0!");
        } catch (NumberFormatException e) {
            System.out.println("Значение должно быть числом!");
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
