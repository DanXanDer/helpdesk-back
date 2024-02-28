package portfolio.helpdesk.mappers;

import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.UserCreationDTO;
import portfolio.helpdesk.models.User;

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User convertToEntity(UserCreationDTO userCreationDTO);

    UserCreationDTO convertToDTO(User user);
}
