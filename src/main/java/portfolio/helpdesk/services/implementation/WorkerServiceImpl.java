package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.models.Worker;
import portfolio.helpdesk.repositories.IWorkerRepo;
import portfolio.helpdesk.services.IWorkerService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WorkerServiceImpl extends CrudImpl<Worker, Integer> implements IWorkerService {
    private final IWorkerRepo workerRepo;

    @Override
    protected IWorkerRepo getRepo() {
        return workerRepo;
    }

    @Override
    public List<Worker> findAll() {
        return getRepo().findAll();
    }
}
