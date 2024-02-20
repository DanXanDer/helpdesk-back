package portfolio.helpdesk.repositories;

import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.User;

@Repository
public interface IUserRepo extends IGenericRepo<User, Integer> {

}
