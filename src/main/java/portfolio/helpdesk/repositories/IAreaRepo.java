package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.models.Campus;

import java.util.Optional;

public interface IAreaRepo extends IGenericRepo<Area, Integer> {

    @Query("SELECT a FROM Area a WHERE a.name = ?1 AND a.campus.idCampus = ?2")
    Optional<Area> findAreaByNameAndIdCampus(String name, Integer idCampus);
}
