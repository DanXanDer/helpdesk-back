package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Branch;

public interface IBranchService extends ICRUD<Branch, Integer> {
    void findBranchByNameAndCompany(String name, Integer idCompany);
}
