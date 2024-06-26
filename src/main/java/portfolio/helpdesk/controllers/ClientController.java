package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.ClientRequestDTO;
import portfolio.helpdesk.DTO.response.ClientResponseDTO;
import portfolio.helpdesk.mappers.ClientMapper;
import portfolio.helpdesk.services.IClientService;
import portfolio.helpdesk.services.IUserService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {
    private final IUserService userService;
    private final IClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ClientRequestDTO client) {
        userService.findByUsernameOrEmail(client.user().getUsername(), client.user().getEmail(), null);
        userService.validatePasswords(client.user().getPassword(), client.user().getRePassword());
        Integer idClient = clientService.save(clientMapper.convertToEntity(client)).getId();
        URI location = URI.create(String.format("/clients/%d", idClient));
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> findAll() {
        return ResponseEntity.ok(clientService.findAll().stream().map(clientMapper::convertToDTO).toList());
    }

    @GetMapping("/{idClient}")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Integer idClient) {
        return ResponseEntity.ok(clientMapper.convertToDTO(clientService.findById(idClient)));
    }

}
