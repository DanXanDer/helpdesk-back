package portfolio.helpdesk.mappers;

import org.mapstruct.*;
import portfolio.helpdesk.DTO.request.BranchRequestDTO;
import portfolio.helpdesk.DTO.request.BranchUpdateDTO;
import portfolio.helpdesk.DTO.response.BranchResponseDTO;
import portfolio.helpdesk.models.Branch;

@Mapper(componentModel = "spring", uses = {AreaMapper.class})
public abstract class BranchMapper {

    public abstract Branch convertToEntity(BranchRequestDTO branchRequestDTO, @Context CycleAvoidingMappingContext context);

    public abstract BranchResponseDTO convertToDTO(Branch branch, @Context CycleAvoidingMappingContext context);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDTO(BranchUpdateDTO branchUpdateDTO, @MappingTarget Branch branch);

}
