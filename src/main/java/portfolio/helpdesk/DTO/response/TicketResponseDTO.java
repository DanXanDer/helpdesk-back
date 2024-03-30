package portfolio.helpdesk.DTO.response;

import portfolio.helpdesk.models.TicketStatus;

import java.time.LocalDateTime;

public record TicketResponseDTO(
        Integer idTicket,
        ClientResponseDTO client,
        WorkerResponseDTO worker,
        String summary,
        String description,
        TicketStatus ticketStatus,
        LocalDateTime creationDate,
        LocalDateTime lastUpdate
) {
}
