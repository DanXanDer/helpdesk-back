package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.AreaCreationDTO;
import portfolio.helpdesk.DTO.response.AreaResponseDTO;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.models.Branch;

@Mapper
public interface AreaMapper {
    AreaMapper INSTANCE = Mappers.getMapper(AreaMapper.class);

    default Area convertToEntity(AreaCreationDTO areaCreationDTO) {
        Branch branch = new Branch();
        branch.setIdBranch(areaCreationDTO.idBranch());
        Area area = new Area();
        area.setName(areaCreationDTO.name());
        area.setBranch(branch);
        return area;
    }

    @Mapping(target = "branchName", source = "branch.name")
    AreaResponseDTO convertToDTO(Area area);
}
