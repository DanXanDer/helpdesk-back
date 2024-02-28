package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.models.Worker;
import portfolio.helpdesk.repositories.IWorkerRepo;
import portfolio.helpdesk.services.IWorkerService;

@RequiredArgsConstructor
@Service
public class WorkerServiceImpl extends CrudImpl<Worker, Integer> implements IWorkerService {
    private final IWorkerRepo workerRepo;

    @Override
    protected IWorkerRepo getRepo() {
        return workerRepo;
    }
}
