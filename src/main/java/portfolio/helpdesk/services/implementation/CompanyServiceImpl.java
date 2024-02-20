package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.models.Company;
import portfolio.helpdesk.repositories.ICompanyRepo;
import portfolio.helpdesk.repositories.IGenericRepo;
import portfolio.helpdesk.services.ICompanyService;

@RequiredArgsConstructor
@Service
public class CompanyServiceImpl extends CrudImpl<Company, Integer> implements ICompanyService {

    private final ICompanyRepo companyRepo;

    @Override
    protected IGenericRepo<Company, Integer> getRepo() {
        return companyRepo;
    }
}
