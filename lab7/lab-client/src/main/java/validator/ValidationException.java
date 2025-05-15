package validator;

/**
 * Исключение, выбрасываемое при некорректном вводе значения
 */
public class ValidationException extends RuntimeException {
    /**
     * Конструктор
     *
     * @param message - сообщение
     */
    public ValidationException(String message) {
        super(message);
    }
}
