package moduls.validator;

/**
 * Класс проверки значения поля x
 */
public class XValidator implements Validator {
    /**
     * Проверка значения
     *
     * @param line - обрабатываемая строка
     * @return true, если введено корректное значение, и false, в противном случае
     */
    @Override
    public boolean validate(String line) {
        try {
            if (Float.parseFloat(line) > -626) {
                return true;
            }
            throw new ValidationException("Значение поля x должно быть больше -626!");
        } catch (NumberFormatException e) {
            System.out.println("Значение поля должно быть числом типа Float!");
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
