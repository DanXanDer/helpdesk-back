package portfolio.helpdesk.DTO.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AreaRequestDTO {
    Integer idBranch;

    @Size(min = 3, max = 100, message = "Nombre del área debe tener entre 2 y 100 caracteres")
    String name;


}
