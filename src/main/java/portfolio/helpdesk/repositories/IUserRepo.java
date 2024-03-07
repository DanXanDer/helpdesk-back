package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.UserData;

import java.util.Optional;

@Repository
public interface IUserRepo extends IGenericRepo<UserData, Integer> {
    @Query("SELECT u FROM UserData u WHERE u.username = ?1 OR u.email = ?2")
    Optional<UserData> findByUsernameOrEmail(String username, String email);

    @Query("SELECT u FROM UserData u WHERE u.username = ?1 AND u.name = ?2 AND u.lastname = ?3")
    Optional<UserData> findByValidationData(String username, String name, String lastname);
}
