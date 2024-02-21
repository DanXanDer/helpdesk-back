package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Company;

public interface ICompanyService extends ICRUD<Company, Integer> {
    void findCompanyByName(String name);
    Company update(Integer id, Company company);
}
