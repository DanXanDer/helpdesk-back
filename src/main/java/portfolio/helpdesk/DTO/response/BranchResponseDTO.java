package portfolio.helpdesk.DTO.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.List;

@Data
public class BranchResponseDTO {
    private Integer idBranch;

    @JsonBackReference
    private CompanyResponseDTO company;

    private String name;

    private boolean enabled;

    @JsonManagedReference
    private List<AreaResponseDTO> areas;
}
