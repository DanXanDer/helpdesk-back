package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.WorkerRequestDTO;
import portfolio.helpdesk.DTO.response.WorkerResponseDTO;
import portfolio.helpdesk.mappers.WorkerMapper;
import portfolio.helpdesk.services.IUserService;
import portfolio.helpdesk.services.IWorkerService;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workers")
public class WorkerController {
    private final IUserService userService;
    private final IWorkerService workerService;
    private final WorkerMapper workerMapper;

    @Transactional
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody WorkerRequestDTO workerRequestDTO) {
        userService.findByUsernameOrEmail(workerRequestDTO.user().getUsername(), workerRequestDTO.user().getEmail());
        userService.validatePasswords(workerRequestDTO.user().getPassword(), workerRequestDTO.user().getRePassword());
        Integer idWorker = workerService.save(workerMapper.convertToEntity(workerRequestDTO)).getUserData().getId();
        URI location = URI.create(String.format("/workers/%d", idWorker));
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<WorkerResponseDTO>> findAll() {
        return ResponseEntity.ok(workerService.findAll().stream().map(workerMapper::convertToDTO).toList());
    }
}
