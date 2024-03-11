package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;

public record WorkerCreationDTO(
        @NotNull(message = "El id del usuario no puede ser nulo")
        Integer id
) {
}
