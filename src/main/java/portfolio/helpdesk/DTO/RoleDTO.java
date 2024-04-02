package portfolio.helpdesk.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RoleDTO(
        Integer idRole,
        String authority,
        String description
) {

}
