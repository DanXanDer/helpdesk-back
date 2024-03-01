package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreationDTO(
        @NotNull
        @Size(min = 3, max = 100, message = "El nombre de usuario debe tener entre 3 y 100 caracteres.")
        String username,
        @NotNull
        @Size(min = 8, max = 100, message = "La contraseña debe tener entre 3 y 100 caracteres.")
        String password,
        @NotNull
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres.")
        String name,
        @NotNull
        @Size(min = 3, max = 100, message = "El apellido debe tener entre 3 y 100 caracteres.")
        String lastname,
        @NotNull
        @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "El correo electrónico no es válido.")
        String email,
        @NotNull
        @Size(min = 3, max = 100, message = "El tipo de usuario debe tener entre 3 y 100 caracteres.")
        String type
) {
}
