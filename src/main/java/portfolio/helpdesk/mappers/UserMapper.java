package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

   /* User convertToEntity(UserRequestDTO userRequestDTO);

   *//* User convertToEntity(UserRequestDTO userRequestDTO);

    UserResponseDTO convertToDTO(User user);*/
}
