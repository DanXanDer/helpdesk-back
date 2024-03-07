package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.CompanyCreationDTO;
import portfolio.helpdesk.DTO.request.CompanyUpdateDTO;
import portfolio.helpdesk.DTO.response.CompanyResponse;
import portfolio.helpdesk.models.Branch;
import portfolio.helpdesk.models.Company;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);
    BranchMapper branchMapper = BranchMapper.INSTANCE;

    default Company convertToEntity(CompanyCreationDTO companyCreationDTO) {
        Company company = new Company();
        company.setName(companyCreationDTO.name());
        Set<Branch> branches = companyCreationDTO.branches().stream().map(branchMapper::convertToEntity).collect(Collectors.toSet());
        branches.forEach(branch -> branch.setCompany(company));
        company.setBranches(branches);
        return company;
    }

    default CompanyResponse convertToDTO(Company company) {
        return new CompanyResponse(
                company.getIdCompany(),
                company.getName(),
                company.isEnabled(),
                company.getBranches().stream().map(branchMapper::convertToDTO).collect(Collectors.toSet())
        );
    }

    void updateFromDTO(CompanyUpdateDTO companyUpdateDTO, @MappingTarget Company company);

}
