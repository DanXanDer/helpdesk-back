package portfolio.helpdesk.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.security.core.GrantedAuthority;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PrivilegeDTO(
        Integer idPrivilege,
        String icon,
        String authority,
        String url) implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return authority();
    }
}
