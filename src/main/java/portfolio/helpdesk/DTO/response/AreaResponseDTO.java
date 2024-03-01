package portfolio.helpdesk.DTO.response;

public record AreaResponseDTO(
        Integer idArea,
        String branchName,
        String name,
        boolean enabled) {
}
