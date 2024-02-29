package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.AreaRequestDTO;
import portfolio.helpdesk.DTO.response.AreaResponseDTO;
import portfolio.helpdesk.models.Area;

@Mapper
public interface AreaMapper {
    AreaMapper INSTANCE = Mappers.getMapper(AreaMapper.class);

    Area convertToEntity(AreaRequestDTO areaRequestDTO);

    AreaResponseDTO convertToDTO(Area area);
}
