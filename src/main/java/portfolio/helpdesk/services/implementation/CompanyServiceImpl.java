package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.helpdesk.DTO.request.CompanyRequestDTO;
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
    public void findCompanyByName(String name) {
        getRepo().findCompanyByName(name).ifPresent(company -> {
            throw new ModelAlreadyExistsException("Company with name " + name + " already exists");
        });
    }

    @Override
    @Transactional
    public void updateCompanyNameByIdCompany(Integer idCompany, String name) {
        getRepo().findCompanyByName(name).ifPresent(company -> {
            throw new ModelAlreadyExistsException("Company with name " + name + " already exists");
        });
        getRepo().updateCompanyNameByIdCompany(idCompany, name);
    }

    @Override
    @Transactional
    public void updateCompanyStatusByIdCompany(Integer idCompany, boolean status) {
        getRepo().findById(idCompany).orElseThrow(() -> new ModelNotFoundException("Company with id " + idCompany + " not found"));
        getRepo().updateCompanyStatusByIdCompany(idCompany, status);
    }


}
