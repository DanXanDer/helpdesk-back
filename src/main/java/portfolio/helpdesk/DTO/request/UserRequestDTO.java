package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import portfolio.helpdesk.DTO.RoleDTO;

@Data
public class UserRequestDTO {
    @NotNull(message = "El rol no puede ser nulo")
    private RoleDTO role;
    @Size(min = 3, max = 100, message = "El nombre de usuario debe tener entre 3 y 100 caracteres.")
    private String username;
    @Size(min = 5, max = 100, message = "La contraseña debe tener entre 3 y 100 caracteres.")
    private String password;
    @Size(min = 5, max = 100, message = "La contraseña debe tener entre 3 y 100 caracteres.")
    private String rePassword;
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres.")
    private String name;
    @Size(min = 3, max = 100, message = "El apellido debe tener entre 3 y 100 caracteres.")
    private String lastname;
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "El correo electrónico no es válido.")
    private String email;
}
