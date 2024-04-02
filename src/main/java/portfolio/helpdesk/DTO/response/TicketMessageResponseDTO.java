package portfolio.helpdesk.DTO.response;

import portfolio.helpdesk.DTO.TicketImageDTO;

import java.util.List;

public record TicketMessageResponseDTO(
        Integer id,
        String message,
        String sender,
        String receiver,
        String sentDate,
        Integer idTicket,
        List<TicketImageDTO> images
) {
}
