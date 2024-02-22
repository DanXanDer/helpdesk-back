package portfolio.helpdesk.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AreaDTO {

    private int idArea;

    @NotNull
    private int idCampus;

    @NotNull(message = "Nombre del área no puede ser nulo")
    @Size(min = 3, max = 100, message = "Nombre del área debe tener entre 2 y 100 caracteres")
    private String name;
    private boolean enabled;

}