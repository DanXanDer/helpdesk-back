package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import portfolio.helpdesk.models.Area;

import java.util.List;
import java.util.Optional;

public interface IAreaRepo extends IGenericRepo<Area, Integer> {

    @Query("SELECT a FROM Area a WHERE a.name = ?1 AND a.campus.idCampus = ?2")
    Optional<Area> findAreaByNameAndIdCampus(String name, Integer idCampus);

    @Modifying
    @Query("UPDATE Area a SET a.enabled = ?2 WHERE a.idArea = ?1")
    void updateAreaStatusByIdArea(Integer idArea, boolean status);

    @Query("SELECT a FROM Area a WHERE a.campus.idCampus = ?1")
    List<Area> findAllAreasByIdCampus(Integer idCampus);
}
