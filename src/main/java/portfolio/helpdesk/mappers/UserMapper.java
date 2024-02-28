package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.UserRequestDTO;
import portfolio.helpdesk.DTO.response.UserResponseDTO;
import portfolio.helpdesk.models.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User convertToEntity(UserRequestDTO userRequestDTO);

    UserResponseDTO convertToDTO(User user);
}
