package portfolio.helpdesk.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import portfolio.helpdesk.DTO.request.CompanyRequestDTO;
import portfolio.helpdesk.DTO.request.CompanyUpdateDTO;
import portfolio.helpdesk.DTO.response.CompanyResponseDTO;
import portfolio.helpdesk.models.Branch;
import portfolio.helpdesk.models.Company;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BranchMapper.class})
public abstract class CompanyMapper {
    @Autowired
    private BranchMapper branchMapper;

    public Company convertToEntity(CompanyRequestDTO companyRequestDTO) {
        Company company = new Company();
        company.setName(companyRequestDTO.getName());
        List<Branch> branches = branchMapper.convertToEntityList(companyRequestDTO.getBranches());
        branches.forEach(branch -> branch.setCompany(company));
        company.setBranches(branches);
        return company;
    }

    public abstract CompanyResponseDTO convertToDTO(Company company);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDTO(CompanyUpdateDTO companyUpdateDTO, @MappingTarget Company company);

}
