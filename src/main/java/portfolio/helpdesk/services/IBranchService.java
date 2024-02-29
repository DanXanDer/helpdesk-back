package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Branch;

import java.util.List;

public interface IBranchService extends ICRUD<Branch, Integer> {
    void findBranchByNameAndCompany(String name, Integer idCompany);

    void updateBranchStatusByCompanyStatus(Integer idCompany, boolean status);

    void updateBranchStatusByIdBranch(Integer idBranch, boolean status);

    List<Branch> findAllBranchByIdCompany(Integer idCompany);
}
