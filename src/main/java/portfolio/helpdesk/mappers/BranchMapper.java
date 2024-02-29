package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.AreaCreationDTO;
import portfolio.helpdesk.DTO.request.BranchCreationDTO;
import portfolio.helpdesk.DTO.response.BranchResponseDTO;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.models.Branch;
import portfolio.helpdesk.models.Company;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface BranchMapper {
    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    default Branch convertToEntity(BranchCreationDTO branchCreationDTO) {
        Company company = new Company();
        company.setIdCompany(branchCreationDTO.idCompany());
        Branch branch = new Branch();
        branch.setName(branchCreationDTO.name());
        branch.setCompany(company);
        Set<Area> areasList = branchCreationDTO.areas().stream().map(this::convertToEntity).collect(Collectors.toSet());
        areasList.forEach(area -> area.setBranch(branch));
        branch.setAreas(areasList);
        return branch;
    }

    default Area convertToEntity(AreaCreationDTO areaCreationDTO) {
        Area area = new Area();
        area.setName(areaCreationDTO.name());
        return area;
    }

    BranchResponseDTO convertToDTO(Branch branch);
}
