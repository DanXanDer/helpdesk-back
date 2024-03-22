package portfolio.helpdesk.mappers;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import portfolio.helpdesk.DTO.PrivilegeDTO;
import portfolio.helpdesk.DTO.request.UserRequestDTO;
import portfolio.helpdesk.DTO.request.UserUpdateDTO;
import portfolio.helpdesk.DTO.response.UserSecretQuestionResponseDTO;
import portfolio.helpdesk.configuration.IgnoreUnmappedMapperConfig;
import portfolio.helpdesk.models.UserData;
import portfolio.helpdesk.security.CustomUserDetails;

import java.util.Set;

@Mapper(componentModel = "spring",
        config = IgnoreUnmappedMapperConfig.class,
        uses = {RoleMapper.class, SecretQuestionMapper.class}
)
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder encoder;

    @Mapping(target = "password", expression = "java(encoder.encode(userRequestDTO.getPassword()))")
    public abstract UserData convertToEntity(UserRequestDTO userRequestDTO);

    @Mapping(target = "role", source = "userData.role.name")
    public abstract CustomUserDetails convertToCustomUserDetails(UserData userData, Set<PrivilegeDTO> authorities);

    @Mapping(target = "secretQuestion", source = "secretQuestion.name")
    public abstract UserSecretQuestionResponseDTO convertToUserSecretQuestionResponseDTO(UserData userData);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", expression = "java(userUpdateDTO.getPassword() != null ? encoder.encode(userUpdateDTO.getPassword()) : userData.getPassword())")
    @Mapping(target = "secretAnswer", expression = "java(userUpdateDTO.getSecretAnswer() != null ? encoder.encode(userUpdateDTO.getSecretAnswer()) : userData.getSecretAnswer())")
    public abstract void updateFromDTO(UserUpdateDTO userUpdateDTO, @MappingTarget UserData userData);
}
