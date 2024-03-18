package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Query;
import portfolio.helpdesk.models.Branch;

import java.util.List;
import java.util.Optional;

public interface IBranchRepo extends IGenericRepo<Branch, Integer> {

    @Query("SELECT b FROM Branch b WHERE b.name = :name AND b.company.idCompany = :idCompany")
    Optional<Branch> findByNameAndCompany(String name, Integer idCompany);

    @Query("SELECT b FROM Branch b WHERE b.company.idCompany = :idCompany AND (:enabled IS NULL or b.enabled = :enabled)")
    Optional<List<Branch>> findAllByCompany(Integer idCompany, Boolean enabled);
}
