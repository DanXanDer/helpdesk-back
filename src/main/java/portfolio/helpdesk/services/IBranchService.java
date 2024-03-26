package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Branch;

import java.util.List;

public interface IBranchService extends ICRUD<Branch, Integer> {
    void findByNameAndCompany(String name, Integer idCompany, Integer idBranch);

    void updateStatusByCompany(Integer idCompany, Boolean enabled);

    List<Branch> findAllByCompany(Integer idCompany, Boolean enabled);
}
