package portfolio.helpdesk.DTO.response;

public record UserResponseDTO(
        Integer id,
        String username,
        String name,
        String lastname,
        String email,
        String role,
        Boolean enabled
) {
}
