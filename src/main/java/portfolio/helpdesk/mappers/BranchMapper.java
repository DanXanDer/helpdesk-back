package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.BranchCreationDTO;
import portfolio.helpdesk.DTO.response.BranchResponse;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.models.Branch;
import portfolio.helpdesk.models.Company;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface BranchMapper {
    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);
    AreaMapper areaMapper = AreaMapper.INSTANCE;

    default Branch convertToEntity(BranchCreationDTO branchCreationDTO) {
        Company company = new Company();
        company.setIdCompany(branchCreationDTO.idCompany());
        Branch branch = new Branch();
        branch.setName(branchCreationDTO.name());
        branch.setCompany(company);
        Set<Area> areas = branchCreationDTO.areas().stream().map(areaMapper::convertToEntity).collect(Collectors.toSet());
        areas.forEach(area -> area.setBranch(branch));
        branch.setAreas(areas);
        return branch;
    }

    default BranchResponse convertToDTO(Branch branch) {
        return new BranchResponse(
                branch.getIdBranch(),
                branch.getCompany().getName(),
                branch.getName(),
                branch.isEnabled(),
                branch.getAreas().stream().map(areaMapper::convertToDTO).collect(Collectors.toSet())
        );
    }

}
