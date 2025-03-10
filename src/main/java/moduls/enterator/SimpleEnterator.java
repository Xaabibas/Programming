package moduls.enterator;

import moduls.validator.Validator;

import java.util.Scanner;

/**
 * Интерфейс для ввода необходимого поля
 *
 * @param <T> вводимый тип
 */
public interface SimpleEnterator<T> {
    /**
     * @param scanner - сканер
     * @param validator - валидатор
     * @return возвращает введенное пользователем значение поля
     */
    T enter(Scanner scanner, Validator validator);
}
