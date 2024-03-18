package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.response.PrivilegeResponseDTO;
import portfolio.helpdesk.models.Privilege;

@Mapper
public interface PrivilegeMapper {

    PrivilegeMapper INSTANCE = Mappers.getMapper(PrivilegeMapper.class);

    PrivilegeResponseDTO convertToDTO(Privilege privilege);
}
