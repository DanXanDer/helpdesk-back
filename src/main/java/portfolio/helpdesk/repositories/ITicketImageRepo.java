package portfolio.helpdesk.repositories;

import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.TicketImage;

@Repository
public interface ITicketImageRepo extends IGenericRepo<TicketImage, Integer> {
}
