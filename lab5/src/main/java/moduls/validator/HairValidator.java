package moduls.validator;

import moduls.HairColor;

/**
 * Класс проверки значения вводимого поля hairColor
 */
public class HairValidator implements Validator {
    /**
     * Проверка значения
     *
     * @param line - обрабатываемая строка
     * @return true, если введено корректное значение, и false, в противном случае
     */
    @Override
    public boolean validate(String line) {
        try {
            if (line.isEmpty()) {
                return true;
            }

            HairColor.valueOf(line);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Введите корректное значение!");
        }
        return false;
    }
}
