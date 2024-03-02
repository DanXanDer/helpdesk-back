package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.Size;

public record LoginRequestDTO(
        @Size(min = 3, max = 100)
        String username,
        @Size(min = 3, max = 100)
        String password
) {
}
