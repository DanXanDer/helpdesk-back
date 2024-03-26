package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Query;
import portfolio.helpdesk.models.Area;

import java.util.List;
import java.util.Optional;

public interface IAreaRepo extends IGenericRepo<Area, Integer> {

    @Query("SELECT a FROM Area a WHERE lower(a.name) = lower(:name) AND a.branch.idBranch = :idBranch")
    Optional<Area> findByNameAndBranch(String name, Integer idBranch);

    @Query("SELECT a FROM Area a WHERE a.branch.idBranch = :idBranch AND (:enabled IS NULL or a.enabled = :enabled)")
    Optional<List<Area>> findAllByBranch(Integer idBranch, Boolean enabled);

}
