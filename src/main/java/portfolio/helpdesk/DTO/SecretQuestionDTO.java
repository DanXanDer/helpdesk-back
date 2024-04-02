package portfolio.helpdesk.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecretQuestionDTO {
    Integer idSecretQuestion;

    @NotNull(message = "Nombre de la pregunta de seguridad no puede ser nulo")
    @Size(min = 10, max = 50, message = "La pregunta de seguridad debe tener entre 10 y 100 caracteres")
    String name;
}
