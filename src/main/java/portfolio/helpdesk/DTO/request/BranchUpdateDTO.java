package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BranchUpdateDTO {
    @Size(min = 3, max = 100, message = "Nombre de la empresa debe tener entre 2 y 100 caracteres")
    String name;
    Boolean enabled;
}
