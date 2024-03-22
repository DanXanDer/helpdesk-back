package portfolio.helpdesk.DTO.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BranchRequestDTO {

    @JsonBackReference
    @NotNull(message = "Empresa no puede ser nula")
    CompanyRequestDTO company;

    Integer idBranch;

    @NotNull(message = "Nombre de la empresa no puede ser nulo")
    @Size(min = 3, max = 100, message = "Nombre de la empresa debe tener entre 2 y 100 caracteres")
    String name;

    @JsonManagedReference
    @NotNull(message = "La empresa debe tener al menos un área")
    List<AreaRequestDTO> areas;
}
