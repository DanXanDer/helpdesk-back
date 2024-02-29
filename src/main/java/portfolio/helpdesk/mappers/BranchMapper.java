package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.request.BranchRequestDTO;
import portfolio.helpdesk.DTO.response.BranchResponseDTO;
import portfolio.helpdesk.models.Branch;

@Mapper
public interface BranchMapper {
    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    Branch convertToEntity(BranchRequestDTO branchRequestDTO);

    BranchResponseDTO convertToDTO(Branch branch);
}
