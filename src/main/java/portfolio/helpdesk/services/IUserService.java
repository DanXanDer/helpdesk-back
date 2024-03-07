package portfolio.helpdesk.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import portfolio.helpdesk.DTO.request.UserCreationDTO;
import portfolio.helpdesk.DTO.request.UserUpdateDTO;
import portfolio.helpdesk.DTO.request.ValidateUserDataRequestDTO;
import portfolio.helpdesk.DTO.request.ValidateUserSecretAnswer;
import portfolio.helpdesk.models.UserData;

public interface IUserService extends ICRUD<UserData, Integer>, UserDetailsService {

    void validatePasswords(String password, String rePassword);

    UserData save(UserCreationDTO userCreationDTO);

    void completeRegistration(UserUpdateDTO userUpdateDTO);

    UserData validateUserData(ValidateUserDataRequestDTO validateUserDataRequestDTO);

    void validateSecretAnswer(ValidateUserSecretAnswer validateUserSecretAnswer);

    void restorePassword(UserUpdateDTO userUpdateDTO);
}
