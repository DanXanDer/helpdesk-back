package portfolio.helpdesk.DTO.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserUpdateDTO(
        Integer idSecretQuestion,
        @Size(min = 5, max = 100, message = "La clave debe tener entre 5 y 100 caracteres")
        String password,
        @Size(min = 5, max = 100, message = "La clave debe tener entre 5 y 100 caracteres")
        String rePassword,
        @Size(min = 1, max = 100, message = "La respuesta secreta debe tener entre 1 y 100 caracteres")
        String secretAnswer,
        Boolean enabled,
        Boolean firstLogin
) {
}
