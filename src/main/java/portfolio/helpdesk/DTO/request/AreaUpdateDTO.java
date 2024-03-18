package portfolio.helpdesk.DTO.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AreaUpdateDTO(
        @Size(min = 3, max = 50, message = "El nombre del área debe tener entre 3 y 50 caracteres")
        String name,
        Boolean enabled
) {
}
