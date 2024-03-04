package portfolio.helpdesk.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import portfolio.helpdesk.models.UserData;

public interface IUserService extends ICRUD<UserData, Integer>, UserDetailsService {
    void findByUsernameOrEmail(String username, String email);

    void validatePasswords(String password, String rePassword);

}
