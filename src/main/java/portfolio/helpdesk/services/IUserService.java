package portfolio.helpdesk.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import portfolio.helpdesk.DTO.request.ValidateUserDataRequestDTO;
import portfolio.helpdesk.DTO.request.ValidateUserSecretAnswerDTO;
import portfolio.helpdesk.models.UserData;

public interface IUserService extends ICRUD<UserData, Integer>, UserDetailsService {

    void findByUsernameOrEmail(String username, String email);

    void validatePasswords(String password, String rePassword);

    UserData validateUserData(ValidateUserDataRequestDTO validateUserDataRequestDTO);

    void validateSecretAnswer(Integer id, ValidateUserSecretAnswerDTO validateUserSecretAnswerDTO);

}
