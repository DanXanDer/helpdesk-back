package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import portfolio.helpdesk.DTO.request.ClientRequestDTO;
import portfolio.helpdesk.DTO.response.ClientResponseDTO;
import portfolio.helpdesk.models.Client;

@Mapper(componentModel = "spring", uses = {AreaMapper.class, UserMapper.class})
public abstract class ClientMapper {

    @Mapping(target = "userData", source = "user")
    public abstract Client convertToEntity(ClientRequestDTO client);

    @Mapping(target = "user", source = "userData")
    @Mapping(target = "user.role", source = "userData.role.name")
    @Mapping(target = "company", source = "area.branch.company.name")
    @Mapping(target = "branch", source = "area.branch.name")
    @Mapping(target = "area", source = "area.name")
    public abstract ClientResponseDTO convertToDTO(Client client);

}
