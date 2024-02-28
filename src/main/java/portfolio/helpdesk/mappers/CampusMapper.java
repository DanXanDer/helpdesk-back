package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.CampusRequestDTO;
import portfolio.helpdesk.DTO.response.CampusResponseDTO;
import portfolio.helpdesk.models.Campus;

@Mapper
public interface CampusMapper {
    CampusMapper INSTANCE = Mappers.getMapper(CampusMapper.class);

    @Mapping(source = "idCompany", target = "company.idCompany")
    Campus convertToEntity(CampusRequestDTO campusRequestDTO);

    @Mapping(source = "company.idCompany", target = "idCompany")
    CampusResponseDTO convertToDTO(Campus campus);
}
