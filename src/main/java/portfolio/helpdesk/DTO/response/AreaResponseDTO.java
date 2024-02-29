package portfolio.helpdesk.DTO.response;

public record AreaResponseDTO(
        Integer idArea,
        int idBranch,
        String name,
        boolean enabled) {
}
