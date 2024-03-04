package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.exceptions.ModelNotFoundException;
import portfolio.helpdesk.models.Company;
import portfolio.helpdesk.repositories.ICompanyRepo;
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
    public void findByName(String name) {
        getRepo().findByName(name).ifPresent(company -> {
            throw new ModelAlreadyExistsException("Company with name " + name + " already exists");
        });
    }

    @Override
    @Transactional
    public void updateNameByIdCompany(Integer idCompany, String name) {
        getRepo().findByName(name).ifPresent(company -> {
            throw new ModelAlreadyExistsException("Company with name " + name + " already exists");
        });
        getRepo().updateNameByIdCompany(idCompany, name);
    }

    public void updateStatus(Integer idCompany) {
        Company company = getRepo().findById(idCompany).orElseThrow(ModelNotFoundException::new);
        boolean newStatus = !company.isEnabled();
        company.setEnabled(newStatus);
        company.getBranches().forEach(branch -> {
            branch.setEnabled(newStatus);
            branch.getAreas().forEach(area -> area.setEnabled(newStatus));
        });
        getRepo().save(company);
    }

}
