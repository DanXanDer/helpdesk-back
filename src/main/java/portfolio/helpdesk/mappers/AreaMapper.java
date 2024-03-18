package portfolio.helpdesk.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import portfolio.helpdesk.DTO.request.AreaRequestDTO;
import portfolio.helpdesk.DTO.request.AreaUpdateDTO;
import portfolio.helpdesk.DTO.response.AreaResponseDTO;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.models.Branch;
import portfolio.helpdesk.services.IBranchService;

import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class AreaMapper {

    @Autowired
    private IBranchService branchService;

    public Area convertToEntity(AreaRequestDTO areaRequestDTO) {
        Area area = new Area();
        area.setName(areaRequestDTO.name());
        if (areaRequestDTO.idBranch() != null) {
            Branch branch = branchService.getReferenceById(areaRequestDTO.idBranch());
            area.setBranch(branch);
        }
        return area;
    }

    public abstract Set<Area> convertToEntityList(Set<AreaRequestDTO> areas);

    public abstract AreaResponseDTO convertToDTO(Area area);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDTO(AreaUpdateDTO areaUpdateDTO, @MappingTarget Area area);

}
