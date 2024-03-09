package portfolio.helpdesk.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.UserCreationDTO;
import portfolio.helpdesk.DTO.request.UserUpdateDTO;
import portfolio.helpdesk.DTO.response.PrivilegeResponse;
import portfolio.helpdesk.DTO.response.UserResponse;
import portfolio.helpdesk.DTO.response.ValidateUserDataResponse;
import portfolio.helpdesk.configuration.IgnoreUnmappedMapperConfig;
import portfolio.helpdesk.models.UserData;
import portfolio.helpdesk.security.CustomUserDetails;

import java.util.Set;

@Mapper(config = IgnoreUnmappedMapperConfig.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "role.idRole", source = "idRole")
    UserData convertToEntity(UserCreationDTO userCreationDTO);

    UserResponse convertToUserDTO(UserData userData);

    //@Mapping(target = "authorities", source = "role.privileges")
    CustomUserDetails convertToCustomUserDetails(UserData userData, Set<PrivilegeResponse> authorities);

    @Mapping(target = "secretQuestion", source = "secretQuestion.name")
    ValidateUserDataResponse convertToValidateUserDataDTO(UserData userData);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "secretQuestion.idSecretQuestion", source = "idSecretQuestion")
    void updateFromDTO(UserUpdateDTO userUpdateDTO, @MappingTarget UserData userData);
}
