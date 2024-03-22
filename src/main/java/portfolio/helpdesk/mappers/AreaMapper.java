package portfolio.helpdesk.mappers;

import org.mapstruct.*;
import portfolio.helpdesk.DTO.request.AreaRequestDTO;
import portfolio.helpdesk.DTO.request.AreaUpdateDTO;
import portfolio.helpdesk.DTO.response.AreaResponseDTO;
import portfolio.helpdesk.models.Area;

@Mapper(componentModel = "spring")
public abstract class AreaMapper {


    public abstract Area convertToEntity(AreaRequestDTO areaRequestDTO, @Context CycleAvoidingMappingContext context);

    public abstract AreaResponseDTO convertToDTO(Area area, @Context CycleAvoidingMappingContext context);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDTO(AreaUpdateDTO areaUpdateDTO, @MappingTarget Area area);

}
