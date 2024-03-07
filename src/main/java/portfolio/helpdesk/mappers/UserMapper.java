package portfolio.helpdesk.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.UserCreationDTO;
import portfolio.helpdesk.DTO.request.UserUpdateDTO;
import portfolio.helpdesk.DTO.response.UserResponse;
import portfolio.helpdesk.configuration.IgnoreUnmappedMapperConfig;
import portfolio.helpdesk.models.UserData;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "role.idRole", source = "idRole")
    UserData convertToEntity(UserCreationDTO userCreationDTO);

    UserResponse convertToDTO(UserData userData);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "secretQuestion.idSecretQuestion", source = "idSecretQuestion")
    void updateFromDTO(UserUpdateDTO userUpdateDTO, @MappingTarget UserData userData);

}
