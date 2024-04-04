package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.Ticket;
import portfolio.helpdesk.models.TicketStatus;

import java.util.List;

@Repository
public interface ITicketRepo extends IGenericRepo<Ticket, Integer> {

    @Query("SELECT t from Ticket t " +
            "WHERE (:ticketStatus IS NULL OR t.ticketStatus = :ticketStatus) " +
            "AND (:idClient IS NULL OR t.client.id = :idClient)" +
            "AND (:idWorker IS NULL OR t.worker.id = :idWorker)")
    List<Ticket> findAll(TicketStatus ticketStatus, Integer idClient, Integer idWorker);
}
