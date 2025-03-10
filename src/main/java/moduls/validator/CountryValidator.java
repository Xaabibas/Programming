package moduls.validator;

import moduls.Country;

/**
 * Класс проверки вводимого значения поля nationality
 */
public class CountryValidator implements Validator<Country> {
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
            Country.valueOf(line);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Значение поля введено неверно!");
        }
        return false;
    }
}
