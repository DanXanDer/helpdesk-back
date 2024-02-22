package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.Company;

import java.util.Optional;

@Repository
public interface ICompanyRepo extends IGenericRepo<Company, Integer> {

    Optional<Company> findCompanyByName(String name);

    @Modifying
    @Query("UPDATE Company c SET c.name = ?2 where c.idCompany = ?1")
    void updateCompanyNameByIdCompany(Integer idCompany, String name);

    @Modifying
    @Query("UPDATE Company c SET c.enabled = ?2 where c.idCompany = ?1")
    void updateCompanyStatusByIdCompany(Integer idCompany, boolean status);
}
