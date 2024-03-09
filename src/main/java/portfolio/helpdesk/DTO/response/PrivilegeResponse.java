package portfolio.helpdesk.DTO.response;

import org.springframework.security.core.GrantedAuthority;

public record PrivilegeResponse(
        Integer idPrivilege,
        String icon,
        String authority,
        String url) implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return authority();
    }
}
