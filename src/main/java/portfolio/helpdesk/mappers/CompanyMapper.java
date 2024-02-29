package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.AreaCreationDTO;
import portfolio.helpdesk.DTO.request.BranchCreationDTO;
import portfolio.helpdesk.DTO.request.CompanyCreationDTO;
import portfolio.helpdesk.DTO.response.AreaResponseDTO;
import portfolio.helpdesk.DTO.response.BranchResponseDTO;
import portfolio.helpdesk.DTO.response.CompanyResponseDTO;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.models.Branch;
import portfolio.helpdesk.models.Company;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    default Company convertToEntity(CompanyCreationDTO companyCreationDTO) {
        Company company = new Company();
        company.setName(companyCreationDTO.name());
        Set<Branch> branchList = companyCreationDTO.branches().stream().map(branchRequestDTO -> {
            Branch branch = this.convertToEntity(branchRequestDTO);
            Set<Area> areasList = branchRequestDTO.areas().stream().map(this::convertToEntity).collect(Collectors.toSet());
            branch.setAreas(areasList);
            areasList.forEach(area -> area.setBranch(branch));
            return branch;
        }).collect(Collectors.toSet());
        branchList.forEach(branch -> branch.setCompany(company));
        company.setBranches(branchList);
        return company;
    }

    default Branch convertToEntity(BranchCreationDTO branchCreationDTO) {
        Branch branch = new Branch();
        branch.setName(branchCreationDTO.name());
        return branch;
    }

    default Area convertToEntity(AreaCreationDTO areaCreationDTO) {
        Area area = new Area();
        area.setName(areaCreationDTO.name());
        return area;
    }

    default CompanyResponseDTO convertToDTO(Company company) {
        return new CompanyResponseDTO(
                company.getName(),
                company.isEnabled(),
                company.getBranches().stream().map(this::convertToDTO).collect(Collectors.toSet())
        );
    }

    default BranchResponseDTO convertToDTO(Branch branch) {
        return new BranchResponseDTO(
                branch.getCompany().getName(),
                branch.getName(),
                branch.isEnabled(),
                branch.getAreas().stream().map(this::convertToDTO).collect(Collectors.toSet())
        );
    }

    default AreaResponseDTO convertToDTO(Area area) {
        return new AreaResponseDTO(
                area.getBranch().getName(),
                area.getName(),
                area.isEnabled()
        );
    }
}
