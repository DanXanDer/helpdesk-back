package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.models.TicketImage;
import portfolio.helpdesk.repositories.IGenericRepo;
import portfolio.helpdesk.repositories.ITicketImageRepo;
import portfolio.helpdesk.services.ITicketImageService;

@RequiredArgsConstructor
@Service
public class TicketImageServiceImpl extends CrudImpl<TicketImage, Integer> implements ITicketImageService {
    private final ITicketImageRepo repo;

    @Override
    protected IGenericRepo<TicketImage, Integer> getRepo() {
        return repo;
    }
}
