package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.AreaRequestDTO;
import portfolio.helpdesk.DTO.response.AreaResponseDTO;
import portfolio.helpdesk.models.Area;

@Mapper
public interface AreaMapper {
    AreaMapper INSTANCE = Mappers.getMapper(AreaMapper.class);

    @Mapping(source = "idCampus", target = "campus.idCampus")
    Area convertToEntity(AreaRequestDTO areaRequestDTO);

    @Mapping(source = "campus.idCampus", target = "idCampus" )
    AreaResponseDTO convertToDTO(Area area);
}
