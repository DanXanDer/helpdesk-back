package portfolio.helpdesk.repositories;

import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.Ticket;

@Repository
public interface ITicketRepo extends IGenericRepo<Ticket, Integer> {
}
