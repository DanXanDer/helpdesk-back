package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.exceptions.ModelNotFoundException;
import portfolio.helpdesk.models.Company;
import portfolio.helpdesk.repositories.ICompanyRepo;
import portfolio.helpdesk.services.ICompanyService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompanyServiceImpl extends CrudImpl<Company, Integer> implements ICompanyService {

    private final ICompanyRepo companyRepo;

    @Override
    protected ICompanyRepo getRepo() {
        return companyRepo;
    }

    @Override
    public void validateNameExistence(String name) {
        getRepo().findByName(name).ifPresent(comp -> {
            throw new ModelAlreadyExistsException("Compañía con nombre " + name + " ya existe");
        });
    }

    @Override
    public List<Company> findAll(Boolean enabled) {
        return getRepo().findAll(enabled).orElseThrow(() -> new ModelNotFoundException("No se encontraron compañías"));
    }

    @Override
    public List<Company> findForNoAdmin() {
        return getRepo().findForNoAdmin().orElseThrow(() -> new ModelNotFoundException("No se encontraron compañías"));
    }

}
