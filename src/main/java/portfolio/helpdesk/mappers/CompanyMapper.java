package portfolio.helpdesk.mappers;

import org.mapstruct.factory.Mappers;
import portfolio.helpdesk.DTO.CompanyDTO;
import portfolio.helpdesk.models.Company;

public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);
    Company convertToEntity(CompanyDTO companyDTO);
    CompanyDTO convertToDTO(Company company);
}
