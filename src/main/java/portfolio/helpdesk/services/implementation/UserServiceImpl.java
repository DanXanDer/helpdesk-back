package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.DTO.request.UserCreationDTO;
import portfolio.helpdesk.DTO.request.UserUpdateDTO;
import portfolio.helpdesk.DTO.request.ValidateUserDataRequestDTO;
import portfolio.helpdesk.DTO.request.ValidateUserSecretAnswer;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.exceptions.ModelNotFoundException;
import portfolio.helpdesk.exceptions.PasswordsDontMatchException;
import portfolio.helpdesk.mappers.UserMapper;
import portfolio.helpdesk.models.UserData;
import portfolio.helpdesk.repositories.IUserRepo;
import portfolio.helpdesk.services.IUserService;

import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl extends CrudImpl<UserData, Integer> implements IUserService {

    private final IUserRepo userRepo;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder encoder;

    @Override
    protected IUserRepo getRepo() {
        return userRepo;
    }
    @Override
    public void validatePasswords(String password, String rePassword) {
        if (!password.equals(rePassword)) {
            throw new PasswordsDontMatchException();
        }
    }

    @Override
    public UserData save(UserCreationDTO userCreationDTO) {
        getRepo().findByUsernameOrEmail(userCreationDTO.username(), userCreationDTO.email()).ifPresent(user -> {
            throw new ModelAlreadyExistsException("Usuario o correo electrónico ya existe");
        });
        UserData user = userMapper.convertToEntity(userCreationDTO);
        user.setPassword(encoder.encode(userCreationDTO.password()));
        return getRepo().save(user);
    }

    @Override
    public void completeRegistration(UserUpdateDTO userUpdateDTO) {
        UserData user = getRepo().findById(userUpdateDTO.idUser()).orElseThrow(() -> new ModelNotFoundException("Usuario no encontrado"));
        userMapper.updateFromDTO(userUpdateDTO, user);
        user.setPassword(encoder.encode(userUpdateDTO.password()));
        user.setSecretAnswer(encoder.encode(userUpdateDTO.secretAnswer()));
        getRepo().save(user);
    }

    @Override
    public UserData validateUserData(ValidateUserDataRequestDTO validateUserDataRequestDTO) {
        return getRepo().findByValidationData(validateUserDataRequestDTO.username(), validateUserDataRequestDTO.name(), validateUserDataRequestDTO.lastname())
                .orElseThrow(() -> new ModelNotFoundException("Usuario no encontrado"));
    }

    @Override
    public void validateSecretAnswer(ValidateUserSecretAnswer validateUserSecretAnswer) {
        UserData user = getRepo().findById(validateUserSecretAnswer.idUser()).orElseThrow(() -> new ModelNotFoundException("Usuario no encontrado"));
        if (!encoder.matches(validateUserSecretAnswer.secretAnswer(), user.getSecretAnswer())) {
            throw new ModelNotFoundException("Secret answer is incorrect");
        }
    }

    @Override
    public void restorePassword(UserUpdateDTO userUpdateDTO) {
        UserData user = getRepo().findById(userUpdateDTO.idUser()).orElseThrow(() -> new ModelNotFoundException("Usuario no encontrado"));
        user.setPassword(encoder.encode(userUpdateDTO.password()));
        getRepo().save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserData user = getRepo().findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole().getName()));
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

}
