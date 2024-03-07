package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.helpdesk.DTO.request.CompanyCreationDTO;
import portfolio.helpdesk.DTO.request.CompanyUpdateDTO;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.exceptions.ModelNotFoundException;
import portfolio.helpdesk.mappers.CompanyMapper;
import portfolio.helpdesk.models.Company;
import portfolio.helpdesk.repositories.ICompanyRepo;
import portfolio.helpdesk.services.ICompanyService;

@RequiredArgsConstructor
@Service
public class CompanyServiceImpl extends CrudImpl<Company, Integer> implements ICompanyService {

    private final ICompanyRepo companyRepo;
    private final CompanyMapper companyMapper = CompanyMapper.INSTANCE;

    @Override
    protected ICompanyRepo getRepo() {
        return companyRepo;
    }

    @Override
    @Transactional
    public void updateNameByIdCompany(CompanyUpdateDTO companyUpdateDTO) {
        getRepo().findByName(companyUpdateDTO.name()).ifPresent(comp -> {
            throw new ModelAlreadyExistsException("Company with name " + comp.getName() + " already exists");
        });
        Company company = getRepo().findById(companyUpdateDTO.idCompany()).orElseThrow(() -> new ModelNotFoundException("No se encontró la compañía"));
        companyMapper.updateFromDTO(companyUpdateDTO, company);
        companyRepo.save(company);
    }

    public void updateStatus(Integer idCompany) {
        Company company = getRepo().findById(idCompany).orElseThrow(() -> new ModelNotFoundException("No se encontró la compañía"));
        boolean newStatus = !company.isEnabled();
        company.setEnabled(newStatus);
        company.getBranches().forEach(branch -> {
            branch.setEnabled(newStatus);
            branch.getAreas().forEach(area -> area.setEnabled(newStatus));
        });
        getRepo().save(company);
    }

    @Override
    public Company save(CompanyCreationDTO companyCreationDTO) {
        getRepo().findByName(companyCreationDTO.name()).ifPresent(company -> {
            throw new ModelAlreadyExistsException("La compañía con el nombre de " + company.getName() + " ya existe");
        });
        return getRepo().save(companyMapper.convertToEntity(companyCreationDTO));
    }

}
