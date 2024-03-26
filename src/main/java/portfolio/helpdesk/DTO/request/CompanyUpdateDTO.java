package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.Size;

public record CompanyUpdateDTO(
        @Size(min = 1, max = 100)
        String name,
        Boolean enabled
) {

}
