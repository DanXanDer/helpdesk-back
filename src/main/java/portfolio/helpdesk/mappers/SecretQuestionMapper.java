package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import portfolio.helpdesk.DTO.request.SecretQuestionRequestDTO;
import portfolio.helpdesk.DTO.response.SecretQuestionResponseDTO;
import portfolio.helpdesk.models.SecretQuestion;

@Mapper(componentModel = "spring")
public abstract class SecretQuestionMapper {
    public abstract SecretQuestion convertToEntity(SecretQuestionRequestDTO secretQuestionRequestDTO);

    public abstract SecretQuestionResponseDTO convertToDTO(SecretQuestion secretQuestion);
}
