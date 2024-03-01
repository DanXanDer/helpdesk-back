package portfolio.helpdesk.DTO.response;

public record UserResponseDTO(
        Integer idUser,
        String username,
        String name,
        String lastname,
        String email,
        String type,
        Boolean enabled
) {
}
