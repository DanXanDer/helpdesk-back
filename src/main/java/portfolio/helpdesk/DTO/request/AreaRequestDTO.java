package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

public record AreaRequestDTO(
        CampusRequestDTO campus,
        @NotNull(message = "Nombre del área no puede ser nulo")
        @Size(min = 3, max = 100, message = "Nombre del área debe tener entre 2 y 100 caracteres")
        String name
) {
}
