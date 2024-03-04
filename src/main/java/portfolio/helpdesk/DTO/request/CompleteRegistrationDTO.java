package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CompleteRegistrationDTO(
        @NotNull(message = "El id del usuario no puede ser nulo")
        Integer idUser,
        @NotNull(message = "El id de la pregunta secreta no puede ser nulo")
        Integer idSecretQuestion,
        @Size(min = 5, max = 100, message = "La clave debe tener entre 5 y 100 caracteres")
        String password,
        @Size(min = 5, max = 100, message = "La clave debe tener entre 5 y 100 caracteres")
        String rePassword,
        @NotEmpty(message = "La respuesta secreta no puede ser vacía")
        String secretAnswer
) {
}
