package portfolio.helpdesk.DTO.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyRequestDTO {
    Integer idCompany;

    @Size(min = 3, max = 100, message = "Nombre de la empresa debe tener entre 2 y 100 caracteres")
    String name;

    @Size(min = 1, message = "La empresa debe tener al menos una sede")
    @JsonManagedReference
    List<BranchRequestDTO> branches;
}
