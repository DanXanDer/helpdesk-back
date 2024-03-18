package portfolio.helpdesk.DTO.response;


public record CompanyResponseDTO(
        Integer idCompany,
        String name,
        boolean enabled
) {
}
