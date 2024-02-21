package portfolio.helpdesk.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SecretQuestionDTO {
    private Integer idSecretQuestion;

    @NotNull(message = "Nombre de la pregunta de seguridad no puede ser nulo")
    @Size(min = 10, max = 50, message = "La pregunta de seguridad debe tener entre 10 y 100 caracteres")
    private String name;
}
