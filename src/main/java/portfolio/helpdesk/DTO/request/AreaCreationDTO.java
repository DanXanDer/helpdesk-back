package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AreaCreationDTO(
        Integer idBranch,
        @NotNull(message = "Nombre del área no puede ser nulo")
        @Size(min = 3, max = 100, message = "Nombre del área debe tener entre 2 y 100 caracteres")
        String name
) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AreaCreationDTO branch = (AreaCreationDTO) obj;
        return name.equals(branch.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
