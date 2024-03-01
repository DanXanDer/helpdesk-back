package portfolio.helpdesk.DTO.response;

import java.util.Set;

public record CompanyResponseDTO(
        Integer idCompany,
        String name,
        boolean enabled,
        Set<BranchResponseDTO> branches) {
}
