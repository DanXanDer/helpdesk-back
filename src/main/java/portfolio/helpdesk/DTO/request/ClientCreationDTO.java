package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClientCreationDTO(
        @NotNull
        Integer id,
        @NotNull
        Integer idArea,
        @Size(min = 9, max = 9, message = "El ID de AnyDesk debe tener 9 caracteres")
        String anydesk,
        @Size(min = 9, max = 9, message = "El ID de TeamViewer debe tener 9 caracteres")
        String teamviewer
) {
}
