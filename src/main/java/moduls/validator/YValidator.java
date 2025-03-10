package moduls.validator;

/**
 * Класс проверки корректности ввода поля y
 */
public class YValidator implements Validator {
    /**
     * Проверка значения
     *
     * @param line - обрабатываемая строка
     * @return true, если введено корректное значение, и false, в противном случае
     */
    @Override
    public boolean validate(String line) {
        try {
            Long.parseLong(line);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Значение должно быть числом типа Long!");
        }
        return false;
    }
}
