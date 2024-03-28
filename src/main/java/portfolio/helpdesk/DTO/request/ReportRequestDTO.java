package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportRequestDTO {
    @NotNull(message = "Cliente es requerido")
    private ClientRequestDTO client;
    @Size(min = 5, max = 100, message = "Resumen debe tener entre 5 y 100 caracteres")
    private String resume;
    @Size(min = 5, max = 500, message = "Descripción debe tener entre 5 y 500 caracteres")
    private String description;
    @NotNull(message = "Estado es requerido")
    private LocalDateTime creationDate = LocalDateTime.now();
}
