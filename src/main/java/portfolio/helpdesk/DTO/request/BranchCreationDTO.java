package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record BranchCreationDTO(
        Integer idCompany,
        @NotNull(message = "Dirección de la sede no puede ser nulo")
        @Size(min = 3, max = 100, message = "Nombre de la empresa debe tener entre 2 y 100 caracteres")
        String name,
        @NotNull
        Set<AreaCreationDTO> areas
) {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BranchCreationDTO branch = (BranchCreationDTO) obj;
        return name.equals(branch.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
