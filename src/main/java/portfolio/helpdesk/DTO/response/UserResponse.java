package portfolio.helpdesk.DTO.response;

public record UserResponse(
        Integer idUser,
        String username,
        String name,
        String lastname,
        String email,
        String role,
        Boolean enabled
) {
}
