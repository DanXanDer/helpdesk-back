package portfolio.helpdesk.mappers;

import org.mapstruct.*;
import portfolio.helpdesk.DTO.request.CompanyRequestDTO;
import portfolio.helpdesk.DTO.request.CompanyUpdateDTO;
import portfolio.helpdesk.DTO.response.CompanyInfoResponseDTO;
import portfolio.helpdesk.DTO.response.CompanyResponseDTO;
import portfolio.helpdesk.models.Company;

@Mapper(componentModel = "spring", uses = {BranchMapper.class})
public abstract class CompanyMapper {
    public abstract Company convertToEntity(CompanyRequestDTO companyRequestDTO, @Context CycleAvoidingMappingContext context);

    public abstract CompanyResponseDTO convertToDTO(Company company, @Context CycleAvoidingMappingContext context);

    public abstract CompanyInfoResponseDTO convertToDTO(Company company);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFromDTO(CompanyUpdateDTO companyUpdateDTO, @MappingTarget Company company);

}
