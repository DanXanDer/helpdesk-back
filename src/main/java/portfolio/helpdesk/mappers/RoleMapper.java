package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import portfolio.helpdesk.DTO.response.RoleResponseDTO;
import portfolio.helpdesk.models.Role;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {
    @Mapping(target = "authority", source = "name")
    public abstract RoleResponseDTO convertToDTO(Role role);
}
