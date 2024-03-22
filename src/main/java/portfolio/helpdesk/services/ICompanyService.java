package portfolio.helpdesk.services;
import portfolio.helpdesk.models.Company;

import java.util.List;

public interface ICompanyService extends ICRUD<Company, Integer> {
    void validateNameExistence(String name);
    List<Company> findAll(Boolean enabled);

    List<Company> findForNoAdmin();
}
