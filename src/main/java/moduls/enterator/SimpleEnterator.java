package moduls.enterator;

import moduls.validator.Validator;

import java.util.Scanner;

/**
 * Интерфейс для ввода необходимого поля
 *
 * @param <T> вводимый тип
 */
public interface SimpleEnterator<T> {
    T enter(Scanner scanner, Validator<T> validator);
}
