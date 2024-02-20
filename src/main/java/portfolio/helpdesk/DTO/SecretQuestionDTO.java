package portfolio.helpdesk.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SecretQuestionDTO {
    private Integer idSecretQuestion;

    @NotNull
    @Size(min = 10, max = 50, message = "The question must be between 10 and 50 characters")
    private String name;
}
