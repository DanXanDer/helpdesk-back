package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReportRequestDTO {
    @NotNull(message = "Cliente es requerido")
    private Integer idClient;
    @Size(min = 5, max = 100, message = "Resumen debe tener entre 5 y 100 caracteres")
    private String resume;
    @Size(min = 5, max = 500, message = "Descripción debe tener entre 5 y 500 caracteres")
    private String description;
    private LocalDateTime creationDate;
}
