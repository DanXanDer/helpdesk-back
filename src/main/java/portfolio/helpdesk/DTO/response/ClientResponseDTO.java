package portfolio.helpdesk.DTO.response;

public record ClientResponseDTO(
        UserResponseDTO user,
        String company,
        String branch,
        String area,
        String anydesk,
        String teamviewer
) {
}
