package portfolio.helpdesk.DTO.response;

public record BranchResponseDTO(
        Integer idBranch,
        Integer idCompany,
        String name,
        boolean enabled
) {
}
