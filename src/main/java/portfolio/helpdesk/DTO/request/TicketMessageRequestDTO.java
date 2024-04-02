package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record TicketMessageRequestDTO(
        @NotNull(message = "El mensaje no puede ser nulo")
        @Size(min = 1, max = 500, message = "El mensaje debe tener entre 1 y 500 caracteres")
        String message,
        @NotNull(message = "El remitente no puede ser nulo")
        @Size(min = 1, max = 100, message = "El remitente debe tener entre 1 y 100 caracteres")
        String sender,
        @NotNull(message = "El destinatario no puede ser nulo")
        @Size(min = 1, max = 100, message = "El destinatario debe tener entre 1 y 100 caracteres")
        String receiver,
        @NotNull(message = "El ticket no puede ser nulo")
        Integer idTicket,
        MultipartFile[] images
) {
}
