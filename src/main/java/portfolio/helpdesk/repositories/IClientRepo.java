package portfolio.helpdesk.repositories;

import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.Client;

@Repository
public interface IClientRepo extends IGenericRepo<Client, Integer> {
}
