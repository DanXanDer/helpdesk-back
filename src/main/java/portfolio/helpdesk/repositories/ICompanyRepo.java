package portfolio.helpdesk.repositories;

import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.Company;

@Repository
public interface ICompanyRepo extends IGenericRepo<Company, Integer> {
}
