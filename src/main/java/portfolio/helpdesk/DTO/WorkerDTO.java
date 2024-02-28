package portfolio.helpdesk.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WorkerDTO extends UserCreationDTO {
    private Integer idUser;
}
