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
import portfolio.helpdesk.models.Client;
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
        Client client = clientService.save(clientMapper.convertToEntity(clientRequestDTO));
        URI location = URI.create(String.format("/clients/%d", client.getId()));
        return ResponseEntity.created(location).build();
    }
}
