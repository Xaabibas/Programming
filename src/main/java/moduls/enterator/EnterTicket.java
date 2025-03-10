package moduls.enterator;

import moduls.Ticket;
import moduls.validator.NameValidator;
import moduls.validator.PriceValidator;
import moduls.validator.TypeValidator;

import java.util.Scanner;

/**
 * Вспомогательный класс для ввода объекта класса Ticket
 */
public class EnterTicket implements ComplexEnterator<Ticket> {
    /**
     * @param scanner - сканер
     * @return возвращает введенное пользователем значение типа Ticket
     */
    public Ticket enter(Scanner scanner) {
        Ticket ticket = new Ticket();
        ticket.setName(new EnterName().enter(scanner, new NameValidator()));
        ticket.setCoordinates(new EnterCoordinates().enter(scanner));
        ticket.setPrice(new EnterPrice().enter(scanner, new PriceValidator()));
        ticket.setType(new EnterType().enter(scanner, new TypeValidator()));
        ticket.setPerson(new EnterPerson().enter(scanner));
        return ticket;
    }
}
