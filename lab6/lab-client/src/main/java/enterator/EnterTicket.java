package enterator;

import moduls.Ticket;
import validator.moduls.NameValidator;
import validator.moduls.PriceValidator;
import validator.moduls.TypeValidator;

import java.util.Scanner;

public class EnterTicket implements ComplexEnterator<Ticket> {
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
