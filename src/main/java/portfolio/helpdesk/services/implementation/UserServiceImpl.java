package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.exceptions.PasswordsDontMatchException;
import portfolio.helpdesk.models.UserData;
import portfolio.helpdesk.repositories.IUserRepo;
import portfolio.helpdesk.services.IUserService;

import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl extends CrudImpl<UserData, Integer> implements IUserService {

    private final IUserRepo userRepo;

    @Override
    protected IUserRepo getRepo() {
        return userRepo;
    }

    @Override
    public void findByUsernameOrEmail(String username, String email) {
        getRepo().findByUsernameOrEmail(username, email).ifPresent(user -> {
            throw new ModelAlreadyExistsException("User with this username or email already exists.");
        });
    }

    @Override
    public void validatePasswords(String password, String rePassword) {
        if (!password.equals(rePassword)) {
            throw new PasswordsDontMatchException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserData user = userRepo.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole().getName()));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
