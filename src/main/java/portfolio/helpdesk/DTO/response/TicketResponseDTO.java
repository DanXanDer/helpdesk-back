package portfolio.helpdesk.DTO.response;

import java.time.LocalDateTime;

public record TicketResponseDTO(
        Integer id,
        String client,
        String worker,
        String summary,
        String description,
        String status,
        String color,
        LocalDateTime creationDate,
        LocalDateTime lastUpdate
) {
}
