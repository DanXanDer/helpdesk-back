package portfolio.helpdesk.DTO.response;

import java.util.Set;

public record BranchResponseDTO(
        String companyName,
        String name,
        boolean enabled,
        Set<AreaResponseDTO> areas
) {
}
