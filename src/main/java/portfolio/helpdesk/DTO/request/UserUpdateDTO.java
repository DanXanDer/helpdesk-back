package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import portfolio.helpdesk.DTO.SecretQuestionDTO;

@Data
public class UserUpdateDTO {
    private SecretQuestionDTO secretQuestion;

    @Size(min = 3, max = 100, message = "El nombre de usuario debe tener entre 3 y 100 caracteres.")
    private String username;

    @Size(min = 5, max = 100, message = "La clave debe tener entre 5 y 100 caracteres")
    private String password;

    @Size(min = 5, max = 100, message = "La clave debe tener entre 5 y 100 caracteres")
    private String rePassword;

    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres.")
    private String name;

    @Size(min = 3, max = 100, message = "El apellido debe tener entre 3 y 100 caracteres.")
    private String lastname;

    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "El correo electrónico no es válido.")
    private String email;

    @Size(min = 1, max = 100, message = "La respuesta secreta debe tener entre 1 y 100 caracteres")
    private String secretAnswer;

    private Boolean enabled;

    private Boolean firstLogin;
}
