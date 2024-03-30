package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record TicketRequestDTO(
        @NotNull(message = "Cliente es requerido")
        Integer idClient,
        @NotNull(message = "Resumen es requerido")
        @Size(min = 5, max = 100, message = "Resumen debe tener entre 5 y 100 caracteres")
        String summary,
        @NotNull(message = "Descripción es requerida")
        @Size(min = 5, max = 500, message = "Descripción debe tener entre 5 y 500 caracteres")
        String description,
        MultipartFile[] images
) {
}
