package portfolio.helpdesk.DTO.response;

import java.util.Set;

public record BranchResponse(
        Integer idBranch,
        String companyName,
        String name,
        boolean enabled,
        Set<AreaResponse> areas
) {
}
