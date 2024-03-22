package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import portfolio.helpdesk.DTO.RoleDTO;
import portfolio.helpdesk.models.Role;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {

    public abstract Role convertToEntity(RoleDTO role);

    @Mapping(target = "authority", source = "name")
    public abstract RoleDTO convertToDTO(Role role);
}
