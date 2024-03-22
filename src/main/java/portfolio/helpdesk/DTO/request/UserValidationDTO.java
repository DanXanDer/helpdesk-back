package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserValidationDTO {
    @Size(min = 3, max = 100, message = "El nombre de usuario debe tener entre 3 y 100 caracteres")
    String username;

    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    String name;

    @Size(min = 3, max = 100, message = "El apellido debe tener entre 3 y 100 caracteres")
    String lastname;

    @Size(min = 3, max = 100, message = "La respuesta secreta debe tener entre 3 y 100 caracteres")
    String secretAnswer;
}
