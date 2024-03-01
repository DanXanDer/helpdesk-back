package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;

public record WorkerCreationDTO(
        @NotNull(message = "El usuario no puede ser nulo")
        UserCreationDTO user
) {
}
