package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.ClientCreationDTO;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.models.Client;
import portfolio.helpdesk.models.UserData;

@Mapper
public interface ClientMapper {
    UserMapper userMapper = UserMapper.INSTANCE;
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);


    default Client convertToEntity(ClientCreationDTO clientCreationDTO) {
        UserData userData = userMapper.convertToEntity(clientCreationDTO.user());
        Area area = new Area();
        area.setIdArea(clientCreationDTO.idArea());
        Client client = new Client();
        client.setAnydesk(clientCreationDTO.anydesk());
        client.setTeamviewer(clientCreationDTO.teamviewer());
        client.setArea(area);
        userData.setClient(client);
        client.setUserData(userData);
        return client;
    }
}
