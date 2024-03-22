package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import portfolio.helpdesk.DTO.SecretQuestionDTO;
import portfolio.helpdesk.models.SecretQuestion;

@Mapper(componentModel = "spring")
public abstract class SecretQuestionMapper {
    public abstract SecretQuestion convertToEntity(SecretQuestionDTO secretQuestion);

    public abstract SecretQuestionDTO convertToDTO(SecretQuestion secretQuestion);
}
