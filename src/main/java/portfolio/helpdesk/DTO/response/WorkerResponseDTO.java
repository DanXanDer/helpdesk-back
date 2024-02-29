package portfolio.helpdesk.DTO.response;

public record WorkerResponseDTO(
        Integer idUser,
        String username,
        String name,
        String lastname,
        String email,
        String type,
        Boolean enabled,
        String area,
        String campus,
        String company
) {

}
