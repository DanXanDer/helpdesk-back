package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.helpdesk.DTO.request.ClientRequestDTO;
import portfolio.helpdesk.mappers.ClientMapper;
import portfolio.helpdesk.services.IClientService;

import java.net.URI;

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
}
