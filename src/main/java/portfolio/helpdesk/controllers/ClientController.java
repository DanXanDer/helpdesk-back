package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.helpdesk.DTO.request.ClientCreationDTO;
import portfolio.helpdesk.mappers.ClientMapper;
import portfolio.helpdesk.models.Client;
import portfolio.helpdesk.services.IClientService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final IClientService clientService;
    private final ClientMapper clientMapper = ClientMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ClientCreationDTO clientCreationDTO) {
        Client client = clientService.save(clientMapper.convertToEntity(clientCreationDTO));
        URI location = URI.create(String.format("/clients/%d", client.getIdClient()));
        return ResponseEntity.created(location).build();
    }

}
