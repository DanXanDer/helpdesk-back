package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Query;
import portfolio.helpdesk.models.Role;

import java.util.Set;

public interface IRoleRepo extends IGenericRepo<Role, Integer> {

    @Query("SELECT r FROM Role r WHERE LOWER(r.name) NOT LIKE '%admin%'")
    Set<Role> findRolesNoAdmin();

}
