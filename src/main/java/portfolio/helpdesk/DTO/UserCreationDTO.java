package portfolio.helpdesk.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreationDTO {

    private Integer idUser;

    @NotNull
    @Size(min = 3, max = 50, message = "Nombre de usuario debe tener entre 3 y 50 caracteres")
    private String username;

    @NotNull
    @Size(min = 3, max = 100, message = "Nombre debe tener entre 3 y 100 caracteres")
    private String name;

    @NotNull
    @Size(min = 3, max = 100, message = "Apellido debe tener entre 3 y 100 caracteres")
    private String lastname;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Correo electrónico no válido")
    private String email;

    @NotNull
    @Size(min = 3, max = 100, message = "Tipo de usuario debe tener entre 3 y 100 caracteres")
    private String type;

    private boolean enabled;

}
