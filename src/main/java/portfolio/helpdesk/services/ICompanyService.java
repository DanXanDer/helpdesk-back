package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Company;

public interface ICompanyService extends ICRUD<Company, Integer> {
    void findCompanyByName(String name);
    void updateCompanyNameByIdCompany(Integer idCompany, String name);
    void updateCompanyStatusByIdCompany(Integer idCompany, boolean status);
}
