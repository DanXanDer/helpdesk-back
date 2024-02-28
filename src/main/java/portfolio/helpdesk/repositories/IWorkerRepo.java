package portfolio.helpdesk.repositories;

import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.Worker;

@Repository
public interface IWorkerRepo extends IGenericRepo<Worker, Integer> {
}
