package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record CompanyCreationDTO(
        @NotNull(message = "Nombre de la empresa no puede ser nulo")
        @Size(min = 3, max = 100, message = "Nombre de la empresa debe tener entre 2 y 100 caracteres")
        String name,
        @NotNull
        Set<BranchCreationDTO> branches
) {
}
