package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;

public record RestorePasswordDTO(
        @NotNull(message = "Id del usuario no puede ser nulo")
        Integer idUser,
        @NotNull(message = "Contraseña no puede ser nula")
        String password,
        @NotNull(message = "Repetición de contraseña no puede ser nula")
        String rePassword
) {
}
