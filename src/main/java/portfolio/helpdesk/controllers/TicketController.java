package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import portfolio.helpdesk.DTO.TicketImageDTO;
import portfolio.helpdesk.DTO.request.TicketMessageRequestDTO;
import portfolio.helpdesk.DTO.request.TicketRequestDTO;
import portfolio.helpdesk.DTO.response.TicketResponseDTO;
import portfolio.helpdesk.mappers.TicketMapper;
import portfolio.helpdesk.models.TicketStatus;
import portfolio.helpdesk.services.IFileStorageService;
import portfolio.helpdesk.services.ITicketImageService;
import portfolio.helpdesk.services.ITicketMessageService;
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
    private final ITicketMessageService ticketMessageService;
    private final TicketMapper ticketMapper;
    private final IFileStorageService fileStorageService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> save(@Valid @ModelAttribute TicketRequestDTO ticketRequest) throws IOException {
        Integer idTicket = ticketService.save(ticketMapper.convertToEntity(ticketRequest)).getIdTicket();
        if (ticketRequest.images() != null) {
            Path ticketFolder = fileStorageService.createDirectory(idTicket, "ticket", null);
            for (MultipartFile image : ticketRequest.images()) {
                String url = fileStorageService.save(image, ticketFolder);
                ticketImageService.save(ticketMapper.convertToEntity(new TicketImageDTO(url, idTicket)));
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findTicket(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(ticketMapper.convertToDTO(ticketService.findById(id)));
    }

    @GetMapping(value = "/{id}/image")
    public ResponseEntity<?> findTicketImage(
            @PathVariable("id") Integer id,
            @RequestParam(name = "url") String url) throws IOException {
        System.out.println(url);
        Resource resource = fileStorageService.load(url, "ticket-" + id);
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
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

    @PostMapping(value = "/{id}/message", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> saveMessage(
            @PathVariable Integer id,
            @RequestBody @Valid TicketMessageRequestDTO ticketMessageRequest) throws IOException {
        int idTicketMessage = ticketMessageService.save(ticketMapper.convertToEntity(ticketMessageRequest)).getIdTicketMessage();
        if (ticketMessageRequest.images() != null) {
            Path ticketFolder = fileStorageService.createDirectory(ticketMessageRequest.idTicket(), "ticket", null);
            Path messageFolder = fileStorageService.createDirectory(idTicketMessage, "message", ticketFolder);
            for (MultipartFile image : ticketMessageRequest.images()) {
                String url = fileStorageService.save(image, messageFolder);
                ticketImageService.save(ticketMapper.convertToEntity(new TicketImageDTO(url, id)));
            }
        }
        return ResponseEntity.ok().build();
    }

}
