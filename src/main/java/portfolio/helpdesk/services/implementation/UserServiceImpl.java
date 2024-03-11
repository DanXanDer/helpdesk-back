package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.DTO.request.UserCreationDTO;
import portfolio.helpdesk.DTO.request.UserUpdateDTO;
import portfolio.helpdesk.DTO.request.ValidateUserDataRequestDTO;
import portfolio.helpdesk.DTO.request.ValidateUserSecretAnswerDTO;
import portfolio.helpdesk.DTO.response.PrivilegeResponse;
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
    private final UserMapper userMapper = UserMapper.INSTANCE;
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
            System.out.println(rePassword);
            System.out.println(password);
            throw new PasswordsDontMatchException();
        }
    }

    @Override
    public UserData save(UserCreationDTO userCreationDTO) {
        UserData user = userMapper.convertToEntity(userCreationDTO);
        user.setPassword(encoder.encode(userCreationDTO.password()));
        return getRepo().save(user);
    }
    @Override
    public void completeRegistration(Integer id, UserUpdateDTO userUpdateDTO) {
        UserData user = getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("Usuario no encontrado"));
        userMapper.updateFromDTO(userUpdateDTO, user);
        user.setPassword(encoder.encode(userUpdateDTO.password()));
        user.setSecretAnswer(encoder.encode(userUpdateDTO.secretAnswer()));
        System.out.println(user.getFirstLogin());
        getRepo().save(user);
    }

    @Override
    public UserData validateUserData(ValidateUserDataRequestDTO validateUserDataRequestDTO) {
        return getRepo().findByValidationData(validateUserDataRequestDTO.username(), validateUserDataRequestDTO.name(), validateUserDataRequestDTO.lastname())
                .orElseThrow(() -> new ModelNotFoundException("Usuario no encontrado"));
    }

    @Override
    public void validateSecretAnswer(Integer id, ValidateUserSecretAnswerDTO validateUserSecretAnswerDTO) {
        UserData user = getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("Usuario no encontrado"));
        if (!encoder.matches(validateUserSecretAnswerDTO.secretAnswer(), user.getSecretAnswer())) {
            throw new ModelNotFoundException("La respuesta secreta es incorrecta");
        }
    }

    @Override
    public void restorePassword(Integer id, UserUpdateDTO userUpdateDTO) {
        UserData user = getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("Usuario no encontrado"));
        user.setPassword(encoder.encode(userUpdateDTO.password()));
        getRepo().save(user);
    }

    @Override
    public void changeStatusById(Integer id) {
        UserData user = getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("Usuario no encontrado"));
        user.setEnabled(!user.getEnabled());
        getRepo().save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserData user = getRepo().findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + " no encontrado"));
        Set<PrivilegeResponse> authorities = user.getRole().getPrivileges().stream().map(
                privilegeMapper::convertToDTO).collect(Collectors.toSet());
        return userMapper.convertToCustomUserDetails(user, authorities);
    }
}
