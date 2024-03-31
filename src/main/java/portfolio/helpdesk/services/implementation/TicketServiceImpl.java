package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.models.Ticket;
import portfolio.helpdesk.models.TicketStatus;
import portfolio.helpdesk.repositories.ITicketRepo;
import portfolio.helpdesk.services.ITicketService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl extends CrudImpl<Ticket, Integer> implements ITicketService {

    private final ITicketRepo repo;

    @Override
    protected ITicketRepo getRepo() {
        return repo;
    }

    @Override
    public List<Ticket> findAll(TicketStatus ticketStatus) {
        return repo.findAll(ticketStatus);
    }
}
