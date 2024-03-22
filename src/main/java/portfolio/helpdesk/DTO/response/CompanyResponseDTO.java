package portfolio.helpdesk.DTO.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.List;

@Data
public class CompanyResponseDTO {
    private Integer idCompany;

    private String name;

    @JsonManagedReference
    private List<BranchResponseDTO> branches;

    private boolean enabled;
}
