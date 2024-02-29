package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.AreaRequestDTO;
import portfolio.helpdesk.DTO.request.BranchRequestDTO;
import portfolio.helpdesk.DTO.request.CompanyRequestDTO;
import portfolio.helpdesk.DTO.response.CompanyResponseDTO;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.models.Branch;
import portfolio.helpdesk.models.Company;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    default Company convertToEntity(CompanyRequestDTO companyRequestDTO) {
        Company company = new Company();
        company.setName(companyRequestDTO.name());
        Set<Branch> branchList = companyRequestDTO.branches().stream().map(branchRequestDTO -> {
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

    default Branch convertToEntity(BranchRequestDTO branchRequestDTO) {
        Branch branch = new Branch();
        branch.setName(branchRequestDTO.name());
        return branch;
    }

    default Area convertToEntity(AreaRequestDTO areaRequestDTO) {
        Area area = new Area();
        area.setName(areaRequestDTO.name());
        return area;
    }


    CompanyResponseDTO convertToDTO(Company company);
}
