package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Query;
import portfolio.helpdesk.models.Branch;

import java.util.Optional;

public interface IBranchRepo extends IGenericRepo<Branch, Integer> {

    @Query("SELECT b FROM Branch b WHERE b.name = ?1 AND b.company.idCompany = ?2")
    Optional<Branch> findBranchByNameAndIdCompany(String name, Integer idCompany);
}
