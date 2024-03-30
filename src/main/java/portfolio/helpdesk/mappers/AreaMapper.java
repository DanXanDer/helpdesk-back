package portfolio.helpdesk.mappers;

import org.mapstruct.*;
import portfolio.helpdesk.DTO.request.AreaRequestDTO;
import portfolio.helpdesk.DTO.request.AreaUpdateDTO;
import portfolio.helpdesk.DTO.response.AreaInfoResponseDTO;
import portfolio.helpdesk.DTO.response.AreaResponseDTO;
import portfolio.helpdesk.models.Area;

@Mapper(componentModel = "spring", uses = {ClientMapper.class})
public abstract class AreaMapper {

    public abstract Area convertToEntity(AreaRequestDTO areaRequestDTO, @Context CycleAvoidingMappingContext context);

    public abstract AreaResponseDTO convertToDTO(Area area, @Context CycleAvoidingMappingContext context);

    public abstract AreaInfoResponseDTO convertToDTO(Area area);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDTO(AreaUpdateDTO areaUpdateDTO, @MappingTarget Area area);

}
