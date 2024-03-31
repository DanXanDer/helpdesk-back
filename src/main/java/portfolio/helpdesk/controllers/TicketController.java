package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import portfolio.helpdesk.DTO.TicketImageDTO;
import portfolio.helpdesk.DTO.request.TicketRequestDTO;
import portfolio.helpdesk.DTO.response.TicketResponseDTO;
import portfolio.helpdesk.mappers.TicketImageMapper;
import portfolio.helpdesk.mappers.TicketMapper;
import portfolio.helpdesk.models.TicketStatus;
import portfolio.helpdesk.services.IFileStorageService;
import portfolio.helpdesk.services.ITicketImageService;
import portfolio.helpdesk.services.ITicketService;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final ITicketService ticketService;
    private final ITicketImageService ticketImageService;
    private final TicketMapper ticketMapper;
    private final TicketImageMapper ticketImageMapper;
    private final IFileStorageService fileStorageService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> save(@Valid @ModelAttribute TicketRequestDTO ticketRequest) throws IOException {
        Integer idTicket = ticketService.save(ticketMapper.convertToEntity(ticketRequest)).getIdTicket();
        if (ticketRequest.images() != null) {
            Path ticketFolder = fileStorageService.createDirectory(idTicket, "ticket", null);
            for (MultipartFile image : ticketRequest.images()) {
                String url = fileStorageService.save(image, ticketFolder);
                ticketImageService.save(ticketImageMapper.convertToEntity(new TicketImageDTO(url, idTicket)));
            }
        }
        URI location = URI.create("/tickets/" + idTicket);
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> findAll(
            @RequestParam(value = "status", required = false) TicketStatus status) {
        return ResponseEntity.ok(ticketService.findAll(status).stream().map(ticketMapper::convertToDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> findTicket(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(ticketMapper.convertToDTO(ticketService.findById(id)));
    }

    @GetMapping("/statuses")
    public ResponseEntity<?> findAllTicketStatus() {
        List<Map<String, String>> result;
        List<TicketStatus> ticketStatuses = Arrays.asList(TicketStatus.values());
        result = ticketStatuses.stream().map(ticketStatus -> Map.of(
                "name", ticketStatus.name(),
                "status", ticketStatus.getStatus(),
                "color", ticketStatus.getColor()
        )).toList();
        return ResponseEntity.ok(result);
    }
}
