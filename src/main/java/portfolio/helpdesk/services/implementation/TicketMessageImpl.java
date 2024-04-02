package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.models.TicketMessage;
import portfolio.helpdesk.repositories.ITicketMessageRepo;
import portfolio.helpdesk.services.ITicketMessageService;

@RequiredArgsConstructor
@Service
public class TicketMessageImpl extends CrudImpl<TicketMessage, Integer> implements ITicketMessageService {
    private final ITicketMessageRepo repo;

    @Override
    protected ITicketMessageRepo getRepo() {
        return repo;
    }
}
