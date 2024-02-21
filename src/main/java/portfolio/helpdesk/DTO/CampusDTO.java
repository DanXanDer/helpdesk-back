package portfolio.helpdesk.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CampusDTO {
    private Integer id;

    @NotNull(message = "Id de la empresa no puede ser nulo")
    private Integer idCompany;

    @NotNull(message = "Dirección de la sede no puede ser nulo")
    @Size(min = 3, max = 100, message = "Nombre de la empresa debe tener entre 2 y 100 caracteres")
    private String name;
}
