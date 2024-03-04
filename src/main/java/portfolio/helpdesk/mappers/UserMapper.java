package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.UserCreationDTO;
import portfolio.helpdesk.models.Role;
import portfolio.helpdesk.models.UserData;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    default UserData convertToEntity(UserCreationDTO userCreationDTO) {
        UserData user = new UserData();
        Role role = new Role();
        role.setIdRole(userCreationDTO.idRole());
        user.setUsername(userCreationDTO.username());
        user.setPassword(userCreationDTO.password());
        user.setName(userCreationDTO.name());
        user.setLastname(userCreationDTO.lastname());
        user.setEmail(userCreationDTO.email());
        user.setRole(role);
        return user;
    }


    UserCreationDTO convertToDTO(UserData userData);
}
