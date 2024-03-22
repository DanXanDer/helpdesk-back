package portfolio.helpdesk.DTO;

import org.springframework.security.core.GrantedAuthority;

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
