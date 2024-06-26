package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.UserData;

import java.util.Optional;

@Repository
public interface IUserRepo extends IGenericRepo<UserData, Integer> {
    @Query("SELECT u FROM UserData u WHERE (u.username = :username OR u.email = :email) AND (:id IS NULL OR u.id != :id)")
    Optional<UserData> findByUsernameOrEmail(String username, String email, Integer id);

    @Query("SELECT u FROM UserData u WHERE u.username = :username AND u.name = :name AND u.lastname = :lastname")
    Optional<UserData> findByValidationData(String username, String name, String lastname);

}
