package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import portfolio.helpdesk.models.Campus;

import java.util.List;
import java.util.Optional;

public interface ICampusRepo extends IGenericRepo<Campus, Integer> {
    Optional<Campus> findCampusByNameAndCompany_IdCompany(String name, Integer idCompany);

    @Modifying
    @Query("UPDATE Campus c SET c.enabled = ?2 WHERE c.idCampus = ?1")
    void updateCampusStatusByCompanyStatus(Integer idCampus, boolean status);

    @Modifying
    @Query("SELECT c.idCampus FROM Campus c WHERE c.company.idCompany = ?1")
    List<Integer> findAllIdCampusByIdCompany(Integer idCompany);

}
