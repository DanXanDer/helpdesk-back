package portfolio.helpdesk.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompanyDTO {
    private Integer idCompany;

    @NotNull(message = "Nombre de la empresa no puede ser nulo")
    @Size(min = 3, max = 100, message = "Nombre de la empresa debe tener entre 2 y 100 caracteres")
    private String name;

    private boolean enabled;
}
