package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AreaUpdateDTO {
    @Size(min = 3, max = 50, message = "El nombre del área debe tener entre 3 y 50 caracteres")
    String name;

    Boolean enabled;
}
