package portfolio.helpdesk.mappers;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import portfolio.helpdesk.DTO.request.UserRequestDTO;
import portfolio.helpdesk.DTO.request.UserUpdateDTO;
import portfolio.helpdesk.DTO.response.PrivilegeResponseDTO;
import portfolio.helpdesk.DTO.response.ValidateUserDataResponse;
import portfolio.helpdesk.configuration.IgnoreUnmappedMapperConfig;
import portfolio.helpdesk.models.UserData;
import portfolio.helpdesk.security.CustomUserDetails;
import portfolio.helpdesk.services.ISecretQuestionService;

import java.util.Set;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder encoder;
    @Autowired
    protected ISecretQuestionService secretQuestionService;

    @Mapping(target = "password", expression = "java(encoder.encode(userCreationDTO.password()))")
    @Mapping(target = "role.idRole", source = "idRole")
    public abstract UserData convertToEntity(UserRequestDTO userRequestDTO);

    @Mapping(target = "role", source = "userData.role.name")
    public abstract CustomUserDetails convertToCustomUserDetails(UserData userData, Set<PrivilegeResponseDTO> authorities);

    @Mapping(target = "secretQuestion", source = "secretQuestion.name")
    public abstract ValidateUserDataResponse convertToValidateUserDataDTO(UserData userData);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "secretQuestion", ignore = true)
    @Mapping(target = "password", expression = "java(userUpdateDTO.password() != null ? encoder.encode(userUpdateDTO.password()) : userData.getPassword())")
    @Mapping(target = "secretAnswer", expression = "java(userUpdateDTO.secretAnswer() != null ? encoder.encode(userUpdateDTO.secretAnswer()) : userData.getSecretAnswer())")
    public abstract void updateFromDTO(UserUpdateDTO userUpdateDTO, @MappingTarget UserData userData);
}
