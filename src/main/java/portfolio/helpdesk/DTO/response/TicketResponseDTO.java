package portfolio.helpdesk.DTO.response;

import portfolio.helpdesk.DTO.TicketImageDTO;

import java.time.LocalDateTime;
import java.util.List;

public record TicketResponseDTO(
        Integer id,
        Integer idClient,
        String client,
        Integer idWorker,
        String worker,
        String summary,
        String description,
        String status,
        String color,
        LocalDateTime creationDate,
        LocalDateTime lastUpdate,
        List<TicketMessageResponseDTO> messages,
        List<TicketImageDTO> images
) {
}
