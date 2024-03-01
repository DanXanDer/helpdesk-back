package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.helpdesk.DTO.request.WorkerCreationDTO;
import portfolio.helpdesk.mappers.WorkerMapper;
import portfolio.helpdesk.models.Worker;
import portfolio.helpdesk.services.IWorkerService;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workers")
public class WorkerController {
    private final IWorkerService workerService;
    private final WorkerMapper workerMapper = WorkerMapper.INSTANCE;

    @Transactional
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody WorkerCreationDTO workerCreationDTO) {
        Worker worker = workerService.save(workerMapper.convertToEntity(workerCreationDTO));
        URI location = URI.create(String.format("/workers/%d", worker.getIdWorker()));
        return ResponseEntity.created(location).build();
    }


}
