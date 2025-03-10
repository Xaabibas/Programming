package moduls.validator;

import moduls.EyeColor;

/**
 * Класс проверки значения поля eyeColor
 */
public class EyeValidator implements Validator {

    /**
     * @param line - обрабатываемая строка
     * @return true, если введено корректное значение, и false, в противном случае
     */
    @Override
    public boolean validate(String line) {
        try {
            if (line.isEmpty()) {
                return true;
            }

            EyeColor.valueOf(line);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Введите корректное значение!");
        }
        return false;
    }
}
