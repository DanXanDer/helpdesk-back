package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import portfolio.helpdesk.models.Area;

import java.util.Optional;

public interface IAreaRepo extends IGenericRepo<Area, Integer> {

    @Query("SELECT a FROM Area a WHERE a.name = ?1 AND a.branch.idBranch = ?2")
    Optional<Area> findAreaByNameAndIdBranch(String name, Integer idBranch);

    @Modifying
    @Query("UPDATE Area a SET a.enabled = ?2 WHERE a.idArea = ?1")
    void updateAreaStatusByIdArea(Integer idArea, boolean status);

}
