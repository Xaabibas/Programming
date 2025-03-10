package moduls.validator;

/**
 * Класс проверки значения поля name
 */
public class NameValidator implements Validator {
    /**
     * Проверка значения
     *
     * @param line - обрабатываемая строка
     * @return true, если введено корректное значение, и false, в противном случае
     */
    @Override
    public boolean validate(String line) {
        return !line.isEmpty();
    }
}
