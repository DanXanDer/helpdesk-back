package portfolio.helpdesk.DTO.response;

import java.util.Set;

public record CompanyResponse(
        Integer idCompany,
        String name,
        boolean enabled,
        Set<BranchResponse> branches) {
}
