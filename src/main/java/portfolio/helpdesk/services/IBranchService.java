package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Branch;

public interface IBranchService extends ICRUD<Branch, Integer> {
    void findByNameAndCompany(String name, Integer idCompany);
}
