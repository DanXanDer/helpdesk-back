package portfolio.helpdesk.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import portfolio.helpdesk.models.UserData;

public interface IUserService extends ICRUD<UserData, Integer>, UserDetailsService {

    void findByUsernameOrEmail(String username, String email, Integer id);

    void validatePasswords(String password, String rePassword);

    UserData validateUserData(String username, String name, String lastname);

    void validateSecretAnswer(Integer id, String secretAnswer);

}
