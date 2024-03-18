package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;

public record WorkerRequestDTO(
        @NotNull
        UserRequestDTO user
) {
}
