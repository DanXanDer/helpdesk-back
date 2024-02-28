package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.SecretQuestionRequestDTO;
import portfolio.helpdesk.DTO.response.SecretQuestionResponseDTO;
import portfolio.helpdesk.models.SecretQuestion;

@Mapper
public interface SecretQuestionMapper {

    SecretQuestionMapper INSTANCE = Mappers.getMapper(SecretQuestionMapper.class);
    SecretQuestion convertToEntity(SecretQuestionRequestDTO secretQuestionRequestDTO);
    SecretQuestionResponseDTO convertToDTO(SecretQuestion secretQuestion);
}
