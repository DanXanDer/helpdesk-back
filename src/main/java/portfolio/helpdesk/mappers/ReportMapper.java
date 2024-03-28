package portfolio.helpdesk.mappers;

import org.mapstruct.Mapper;
import portfolio.helpdesk.DTO.request.ReportRequestDTO;
import portfolio.helpdesk.DTO.response.ReportResponseDTO;
import portfolio.helpdesk.models.Report;

@Mapper(componentModel = "spring", uses = {ClientMapper.class})
public abstract class ReportMapper {
    public abstract Report convertToEntity(ReportRequestDTO report);

    public abstract ReportResponseDTO convertToDTO(Report report);
}
