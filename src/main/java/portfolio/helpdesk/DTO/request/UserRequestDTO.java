package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotNull
        @Size(min = 3, max = 50, message = "Nombre de usuario debe tener entre 3 y 50 caracteres")
        String username,
        @NotNull
        @Size(min = 8, max = 100, message = "Contraseña debe tener entre 8 y 100 caracteres")
        String password,
        @NotNull
        @Size(min = 3, max = 100, message = "Nombre debe tener entre 3 y 100 caracteres")
        String name,
        @NotNull
        @Size(min = 3, max = 100, message = "Apellido debe tener entre 3 y 100 caracteres")
        String lastname,
        @NotNull
        @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Correo electrónico no válido")
        String email,
        @NotNull
        @Size(min = 3, max = 100, message = "Tipo de usuario debe tener entre 3 y 100 caracteres")
        String type) {
}
