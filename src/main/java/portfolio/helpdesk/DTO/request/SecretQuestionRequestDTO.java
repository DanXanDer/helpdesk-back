package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SecretQuestionRequestDTO(Integer idSecretQuestion,
                                       @NotNull(message = "Nombre de la pregunta de seguridad no puede ser nulo")
                                       @Size(min = 10, max = 50, message = "La pregunta de seguridad debe tener entre 10 y 100 caracteres")
                                       String name) {
}
