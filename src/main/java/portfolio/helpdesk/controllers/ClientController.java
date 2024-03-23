package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.ClientRequestDTO;
import portfolio.helpdesk.DTO.response.ClientResponseDTO;
import portfolio.helpdesk.mappers.ClientMapper;
import portfolio.helpdesk.services.IClientService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final IClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        Integer idClient = clientService.save(clientMapper.convertToEntity(clientRequestDTO)).getId();
        URI location = URI.create(String.format("/clients/%d", idClient));
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> findAll() {
        return ResponseEntity.ok(clientService.findAll().stream().map(clientMapper::convertToDTO).toList());
    }

}
