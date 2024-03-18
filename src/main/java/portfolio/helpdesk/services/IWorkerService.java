package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Worker;

import java.util.List;

public interface IWorkerService extends ICRUD<Worker, Integer> {
    List<Worker> findAll();
}
