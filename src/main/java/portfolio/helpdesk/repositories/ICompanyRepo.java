package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.Company;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICompanyRepo extends IGenericRepo<Company, Integer> {

    Optional<Company> findByName(String name);

    @Query("SELECT c FROM Company c WHERE (:enabled IS NULL or c.enabled = :enabled)")
    Optional<List<Company>> findAll(@Param("enabled") Boolean enabled);
}
