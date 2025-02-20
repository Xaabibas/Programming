package moduls.enterator;

import moduls.Ticket;

import java.util.Scanner;

public class EnterTicket {
    public static Ticket enterTicket(Scanner sc) {
        Ticket ticket = new Ticket();
        ticket.setName(EnterName.enterName(sc));
        ticket.setCoordinates(EnterCoordinates.enterCoordinates(sc));
        ticket.setPrice(EnterPrice.enterPrice(sc));
        ticket.setType(EnterType.enterType(sc));
        ticket.setPerson(EnterPerson.enterPerson(sc));
        return ticket;
    }
}
