package portfolio.helpdesk.repositories;

import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.User;

import java.util.Optional;

@Repository
public interface IUserRepo extends IGenericRepo<User, Integer> {
    Optional<User> findUserByUsernameOrEmail(String username, String email);
}
