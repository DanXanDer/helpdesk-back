package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ValidateUserSecretAnswer(
        @NotNull(message = "Id del usuario no puede ser nulo")
        Integer idUser,
        @NotEmpty(message = "Respuesta secreta no puede ser vacía")
        String secretAnswer) {

}
