package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record VerifyUserExistenceDTO(
        @NotNull
        @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 5 y 50 caracteres")
        String username,
        @NotNull
        @Pattern(regexp = "^(.+)@(.+)$", message = "El email debe tener un formato válido")
        String email) {
}
