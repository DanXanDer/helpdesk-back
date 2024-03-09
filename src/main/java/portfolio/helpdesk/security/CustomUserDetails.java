package portfolio.helpdesk.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class CustomUserDetails implements UserDetails {
    private Integer idUser;
    private String username;
    private String name;
    private String lastname;
    @JsonIgnore
    private String password;
    private Boolean enabled;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.getEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.getEnabled();
    }

    @Override
    public boolean isEnabled() {
        return this.getEnabled();
    }
}
