package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.UserCreationDTO;
import portfolio.helpdesk.models.UserData;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserData convertToEntity(UserCreationDTO userCreationDTO);

    UserCreationDTO convertToDTO(UserData userData);
}
