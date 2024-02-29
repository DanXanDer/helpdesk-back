package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CompanyNameUpdateDTO(
        @NotNull
        @Size(min = 3, max = 100, message = "Nombre de la empresa debe tener entre 3 y 100 caracteres")
        String name
) {
}
