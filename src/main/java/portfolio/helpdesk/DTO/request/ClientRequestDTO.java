package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClientRequestDTO(

        @NotNull(message = "El usuario no puede ser nulo")
        UserRequestDTO user,

        @NotNull(message = "El are no puede ser nulo")
        AreaRequestDTO area,

        @NotNull(message = "El nombre del cliente no puede ser nulo")
        @Size(min = 9, max = 9, message = "El ID de AnyDesk debe tener 9 caracteres")
        String anydesk,

        @NotNull(message = "El nombre del cliente no puede ser nulo")
        @Size(min = 9, max = 9, message = "El ID de TeamViewer debe tener 9 caracteres")
        String teamviewer
) {
}
