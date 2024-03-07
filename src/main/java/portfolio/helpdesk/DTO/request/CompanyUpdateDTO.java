package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record CompanyUpdateDTO(
        @NotNull
        @Size(min = 3, max = 100)
        String name) {

}
