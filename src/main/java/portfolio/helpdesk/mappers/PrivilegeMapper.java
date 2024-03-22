package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.PrivilegeDTO;
import portfolio.helpdesk.models.Privilege;

@Mapper
public interface PrivilegeMapper {

    PrivilegeMapper INSTANCE = Mappers.getMapper(PrivilegeMapper.class);

    PrivilegeDTO convertToDTO(Privilege privilege);
}
