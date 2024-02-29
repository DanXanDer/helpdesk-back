package portfolio.helpdesk.DTO.response;

public record AreaResponseDTO(
        Integer idArea,
        Integer idBranch,
        String name,
        boolean enabled) {
}
