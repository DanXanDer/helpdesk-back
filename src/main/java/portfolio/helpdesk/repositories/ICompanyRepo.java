package portfolio.helpdesk.repositories;

import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.Company;

import java.util.Optional;

@Repository
public interface ICompanyRepo extends IGenericRepo<Company, Integer> {

    Optional<Company> findByName(String name);

}
