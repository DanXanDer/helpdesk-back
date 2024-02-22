package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.AreaDTO;
import portfolio.helpdesk.models.Area;

@Mapper
public interface AreaMapper {
    AreaMapper INSTANCE = Mappers.getMapper(AreaMapper.class);

    @Mapping(source = "idCampus", target = "campus.idCampus")
    @Mapping(source = "enabled", target = "enabled")
    Area convertToEntity(AreaDTO areaDTO);

    @Mapping(source = "campus.idCampus", target = "idCampus" )
    @Mapping(source = "enabled", target = "enabled")
    AreaDTO convertToDTO(Area area);
}
