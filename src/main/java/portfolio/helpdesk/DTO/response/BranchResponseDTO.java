package portfolio.helpdesk.DTO.response;

import java.util.Set;

public record BranchResponseDTO(
        Integer idBranch,
        Integer idCompany,
        String name,
        boolean enabled,
        Set<AreaResponseDTO> areas
) {
}
