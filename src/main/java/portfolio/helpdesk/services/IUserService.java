package portfolio.helpdesk.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import portfolio.helpdesk.DTO.request.UserCreationDTO;
import portfolio.helpdesk.DTO.request.UserUpdateDTO;
import portfolio.helpdesk.DTO.request.ValidateUserDataRequestDTO;
import portfolio.helpdesk.DTO.request.ValidateUserSecretAnswerDTO;
import portfolio.helpdesk.models.UserData;

public interface IUserService extends ICRUD<UserData, Integer>, UserDetailsService {

    void validatePasswords(String password, String rePassword);

    UserData save(UserCreationDTO userCreationDTO);

    void completeRegistration(Integer idUser, UserUpdateDTO userUpdateDTO);

    UserData validateUserData(ValidateUserDataRequestDTO validateUserDataRequestDTO);

    void validateSecretAnswer(Integer idUser, ValidateUserSecretAnswerDTO validateUserSecretAnswerDTO);

    void restorePassword(Integer idUser, UserUpdateDTO userUpdateDTO);
}
