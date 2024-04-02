package portfolio.helpdesk.repositories;

import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.TicketMessage;

@Repository
public interface ITicketMessageRepo extends IGenericRepo<TicketMessage, Integer> {

}
