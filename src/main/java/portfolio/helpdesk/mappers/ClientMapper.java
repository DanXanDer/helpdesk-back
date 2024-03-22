package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import portfolio.helpdesk.DTO.request.ClientRequestDTO;
import portfolio.helpdesk.models.Client;

@Mapper(componentModel = "spring", uses = {AreaMapper.class, UserMapper.class})
public abstract class ClientMapper {

    public abstract Client convertToEntity(ClientRequestDTO clientRequestDTO);

}
