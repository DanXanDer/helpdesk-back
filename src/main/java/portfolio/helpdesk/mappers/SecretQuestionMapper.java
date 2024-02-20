package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.SecretQuestionDTO;
import portfolio.helpdesk.models.SecretQuestion;

@Mapper
public interface SecretQuestionMapper {

    SecretQuestionMapper INSTANCE = Mappers.getMapper(SecretQuestionMapper.class);
    SecretQuestion convertToEntity(SecretQuestionDTO secretQuestionDTO);
    SecretQuestionDTO convertToDTO(SecretQuestion secretQuestion);
}
