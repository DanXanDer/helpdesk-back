package portfolio.helpdesk.DTO.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CompanyUpdateDTO(
        @NotNull
        @Size(min = 3, max = 100)
        String name,
        Boolean enabled) {

}
