package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.CampusDTO;
import portfolio.helpdesk.models.Campus;

@Mapper
public interface CampusMapper {
    CampusMapper INSTANCE = Mappers.getMapper(CampusMapper.class);

    @Mapping(source = "idCompany", target = "company.idCompany")
    @Mapping(source = "enabled", target = "enabled")
    Campus convertToEntity(CampusDTO campusDTO);

    @Mapping(source = "company.idCompany", target = "idCompany")
    @Mapping(source = "enabled", target = "enabled")
    CampusDTO convertToDTO(Campus campus);
}
