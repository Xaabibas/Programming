package moduls.validator;

/**
 * Интерфейс валидатора
 */
public interface Validator<T> {
    /**
     * Проверка значения
     *
     * @param line - обрабатываемая строка
     * @return true, если введено корректное значение, и false, в противном случае
     */
    boolean validate(String line);
}
