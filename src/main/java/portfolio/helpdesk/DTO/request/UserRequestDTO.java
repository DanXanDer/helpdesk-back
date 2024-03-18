package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotNull(message = "El id del rol puede ser nulo.")
        Integer idRole,
        @Size(min = 3, max = 100, message = "El nombre de usuario debe tener entre 3 y 100 caracteres.")
        String username,
        @Size(min = 5, max = 100, message = "La contraseña debe tener entre 3 y 100 caracteres.")
        String password,
        @Size(min = 5, max = 100, message = "La contraseña debe tener entre 3 y 100 caracteres.")
        String rePassword,
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres.")
        String name,
        @Size(min = 3, max = 100, message = "El apellido debe tener entre 3 y 100 caracteres.")
        String lastname,
        @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "El correo electrónico no es válido.")
        String email

) {
}
