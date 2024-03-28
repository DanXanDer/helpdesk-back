package portfolio.helpdesk.DTO.response;

import java.time.LocalDateTime;

public record ReportResponseDTO(
        Integer idReport,
        ClientResponseDTO client,
        String resume,
        String description,
        LocalDateTime creationDate
) {
}
