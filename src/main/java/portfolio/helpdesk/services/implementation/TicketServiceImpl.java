package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.models.Ticket;
import portfolio.helpdesk.repositories.ITicketRepo;
import portfolio.helpdesk.services.ITicketService;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl extends CrudImpl<Ticket, Integer> implements ITicketService {

    private final ITicketRepo repo;

    @Override
    protected ITicketRepo getRepo() {
        return repo;
    }
}
