package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import portfolio.helpdesk.models.Branch;

import java.util.List;
import java.util.Optional;

public interface IBranchRepo extends IGenericRepo<Branch, Integer> {
    Optional<Branch> findBranchByNameAndCompany_IdCompany(String name, Integer idCompany);

    @Modifying
    @Query("UPDATE Branch c SET c.enabled = ?2 WHERE c.idBranch = ?1")
    void updateBranchStatusByIdBranch(Integer idBranch, boolean status);

    @Query("SELECT c FROM Branch c WHERE c.company.idCompany = ?1")
    List<Branch> findAllBranchByIdCompany(Integer idCompany);

}
