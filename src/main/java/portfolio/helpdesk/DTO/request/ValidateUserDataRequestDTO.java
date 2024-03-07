package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.Size;

public record ValidateUserDataRequestDTO(
        @Size(min = 3, max = 100, message = "El nombre de usuario debe tener entre 3 y 100 caracteres")
        String username,
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        String name,
        @Size(min = 3, max = 100, message = "El apellido debe tener entre 3 y 100 caracteres")
        String lastname
) {
}
