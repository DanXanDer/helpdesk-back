package portfolio.helpdesk.DTO.response;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Integer id;
    private String username;
    private String name;
    private String lastname;
    private String email;
    private String role;
    private Boolean enabled;
}
