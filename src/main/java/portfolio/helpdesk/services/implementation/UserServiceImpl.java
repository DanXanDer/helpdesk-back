package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.DTO.PrivilegeDTO;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.exceptions.ModelNotFoundException;
import portfolio.helpdesk.exceptions.PasswordsDontMatchException;
import portfolio.helpdesk.mappers.PrivilegeMapper;
import portfolio.helpdesk.mappers.UserMapper;
import portfolio.helpdesk.models.UserData;
import portfolio.helpdesk.repositories.IUserRepo;
import portfolio.helpdesk.services.IUserService;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl extends CrudImpl<UserData, Integer> implements IUserService {

    private final IUserRepo userRepo;
    private final UserMapper userMapper;
    private final PrivilegeMapper privilegeMapper = PrivilegeMapper.INSTANCE;
    private final PasswordEncoder encoder;

    @Override
    protected IUserRepo getRepo() {
        return userRepo;
    }

    @Override
    public void findByUsernameOrEmail(String username, String email) {
        getRepo().findByUsernameOrEmail(username, email).ifPresent(user -> {
            throw new ModelAlreadyExistsException("Usuario o correo electrónico ya existe");
        });
    }

    @Override
    public void validatePasswords(String password, String rePassword) {
        if (!password.equals(rePassword)) {
            throw new PasswordsDontMatchException();
        }
    }
    @Override
    public UserData validateUserData(String username, String name, String lastname) {
        return getRepo().findByValidationData(username, name, lastname)
                .orElseThrow(() -> new ModelNotFoundException("Usuario no encontrado"));
    }

    @Override
    public void validateSecretAnswer(Integer id, String secretAnswer) {
        UserData user = getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("Usuario no encontrado"));
        if (!encoder.matches(secretAnswer, user.getSecretAnswer())) {
            throw new ModelNotFoundException("La respuesta secreta es incorrecta");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserData user = getRepo().findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + " no encontrado"));
        Set<PrivilegeDTO> authorities = user.getRole().getPrivileges().stream().map(
                privilegeMapper::convertToDTO).collect(Collectors.toSet());
        return userMapper.convertToCustomUserDetails(user, authorities);
    }
}
