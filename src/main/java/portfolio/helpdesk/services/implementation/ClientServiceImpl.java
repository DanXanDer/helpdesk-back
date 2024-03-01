package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.models.Client;
import portfolio.helpdesk.repositories.IClientRepo;
import portfolio.helpdesk.services.IClientService;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl extends CrudImpl<Client, Integer> implements IClientService {
    private final IClientRepo clientRepo;


    @Override
    protected IClientRepo getRepo() {
        return clientRepo;
    }
}