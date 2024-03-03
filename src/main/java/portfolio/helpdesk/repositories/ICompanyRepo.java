package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.Company;

import java.util.Optional;

@Repository
public interface ICompanyRepo extends IGenericRepo<Company, Integer> {

    @Query("SELECT c FROM Company c WHERE c.name = ?1")
    Optional<Company> findByName(String name);

    @Modifying
    @Query("UPDATE Company c SET c.name = ?2 where c.idCompany = ?1")
    void updateNameByIdCompany(Integer idCompany, String name);

}
