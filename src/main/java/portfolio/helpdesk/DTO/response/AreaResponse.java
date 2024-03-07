package portfolio.helpdesk.DTO.response;

public record AreaResponse(
        Integer idArea,
        String branchName,
        String name,
        boolean enabled) {
}
