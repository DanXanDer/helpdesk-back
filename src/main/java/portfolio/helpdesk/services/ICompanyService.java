package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Company;

public interface ICompanyService extends ICRUD<Company, Integer> {
    void findByName(String name);

    void updateNameByIdCompany(Integer idCompany, String name);
}
