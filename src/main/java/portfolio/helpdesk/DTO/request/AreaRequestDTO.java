package portfolio.helpdesk.DTO.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AreaRequestDTO {

    @JsonBackReference
    BranchRequestDTO branch;

    Integer idArea;

    @Size(min = 3, max = 100, message = "Nombre del área debe tener entre 2 y 100 caracteres")
    @NotNull(message = "Nombre del área no puede ser nulo")
    String name;


}
