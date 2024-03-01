package portfolio.helpdesk.DTO.response;

import java.util.Set;

public record BranchResponseDTO(
        Integer idBranch,
        String companyName,
        String name,
        boolean enabled,
        Set<AreaResponseDTO> areas
) {
}
