package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import portfolio.helpdesk.DTO.request.ClientRequestDTO;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.models.Client;
import portfolio.helpdesk.models.UserData;

@Mapper(componentModel = "spring", uses = {AreaMapper.class, UserMapper.class})
public abstract class ClientMapper {

    public Client convertToEntity(ClientRequestDTO clientRequestDTO) {
        UserData user = new UserData();
        user.setId(clientRequestDTO.id());
        Area area = new Area();
        area.setIdArea(clientRequestDTO.idArea());
        Client client = new Client();
        client.setAnydesk(clientRequestDTO.anydesk());
        client.setTeamviewer(clientRequestDTO.teamviewer());
        client.setArea(area);
        client.setUserData(user);
        return client;
    }
}
