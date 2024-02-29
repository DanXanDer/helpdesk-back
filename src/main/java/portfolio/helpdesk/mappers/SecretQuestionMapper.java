package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.SecretQuestionCreationDTO;
import portfolio.helpdesk.DTO.response.SecretQuestionResponseDTO;
import portfolio.helpdesk.models.SecretQuestion;

@Mapper
public interface SecretQuestionMapper {

    SecretQuestionMapper INSTANCE = Mappers.getMapper(SecretQuestionMapper.class);

    SecretQuestion convertToEntity(SecretQuestionCreationDTO secretQuestionCreationDTO);
    SecretQuestionResponseDTO convertToDTO(SecretQuestion secretQuestion);
}
