package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Ticket;
import portfolio.helpdesk.models.TicketStatus;

import java.util.List;

public interface ITicketService extends ICRUD<Ticket, Integer> {
    List<Ticket> findAll(TicketStatus ticketStatus);
}
