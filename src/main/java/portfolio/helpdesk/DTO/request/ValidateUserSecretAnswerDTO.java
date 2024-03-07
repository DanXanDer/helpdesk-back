package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotEmpty;

public record ValidateUserSecretAnswerDTO(
        @NotEmpty(message = "Respuesta secreta no puede ser vacía")
        String secretAnswer) {

}
