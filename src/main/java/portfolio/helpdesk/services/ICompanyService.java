package portfolio.helpdesk.services;

import portfolio.helpdesk.DTO.request.CompanyCreationDTO;
import portfolio.helpdesk.DTO.request.CompanyUpdateDTO;
import portfolio.helpdesk.models.Company;

public interface ICompanyService extends ICRUD<Company, Integer> {

    void updateNameByIdCompany(CompanyUpdateDTO companyUpdateDTO);

    void updateStatus(Integer idCompany);

    Company save(CompanyCreationDTO companyCreationDTO);
}
