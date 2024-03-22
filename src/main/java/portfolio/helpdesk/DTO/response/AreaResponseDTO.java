package portfolio.helpdesk.DTO.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data
public class AreaResponseDTO {
    @JsonBackReference
    private BranchResponseDTO branch;

    private Integer idArea;

    private String name;

    private boolean enabled;
}
