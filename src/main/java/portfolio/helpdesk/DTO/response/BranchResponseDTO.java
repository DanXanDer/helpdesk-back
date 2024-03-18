package portfolio.helpdesk.DTO.response;

public record BranchResponseDTO(
        Integer idBranch,
        String name,
        boolean enabled
) {
}
