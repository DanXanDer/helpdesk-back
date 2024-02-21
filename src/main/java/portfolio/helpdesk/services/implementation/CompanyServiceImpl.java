package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.exceptions.ModelNotFoundException;
import portfolio.helpdesk.models.Company;
import portfolio.helpdesk.repositories.ICompanyRepo;
import portfolio.helpdesk.repositories.IGenericRepo;
import portfolio.helpdesk.services.ICompanyService;

@RequiredArgsConstructor
@Service
public class CompanyServiceImpl extends CrudImpl<Company, Integer> implements ICompanyService {

    private final ICompanyRepo companyRepo;

    @Override
    protected ICompanyRepo getRepo() {
        return companyRepo;
    }

    @Override
    public void findCompanyByName(String name) {
        getRepo().findCompanyByName(name).ifPresent(company -> {
            throw new ModelAlreadyExistsException("Company with name " + name + " already exists");
        });
    }

    public Company update(Integer id, Company company) {
       getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException(id));
       return getRepo().save(company);
    }
}
