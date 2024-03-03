package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.models.UserData;
import portfolio.helpdesk.repositories.IUserRepo;
import portfolio.helpdesk.services.IUserService;

import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl extends CrudImpl<UserData, Integer> implements IUserService {

    private final IUserRepo userRepo;
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Loading user by username: " + username);
        UserData user = userRepo.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        logger.info("User found: " + user);
        Set<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
