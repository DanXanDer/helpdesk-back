package portfolio.helpdesk.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.response.ValidateUserDataResponseDTO;
import portfolio.helpdesk.models.UserData;

@Mapper
public interface RestorePasswordMapper {
    RestorePasswordMapper INSTANCE = Mappers.getMapper(RestorePasswordMapper.class);

    @Mapping(target = "secretQuestion", source = "secretQuestion.name")
    ValidateUserDataResponseDTO convertToDTO(UserData userData);
}
