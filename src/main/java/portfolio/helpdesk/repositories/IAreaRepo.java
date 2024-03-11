package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Query;
import portfolio.helpdesk.models.Area;

import java.util.Optional;

public interface IAreaRepo extends IGenericRepo<Area, Integer> {

    @Query("SELECT a FROM Area a WHERE a.name = ?1 AND a.branch.idBranch = ?2")
    Optional<Area> findByNameAndIdBranch(String name, Integer idBranch);

}
