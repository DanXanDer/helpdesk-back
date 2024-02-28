package portfolio.helpdesk.DTO.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

public record CampusRequestDTO(Integer idCampus,
                              @NotNull(message = "Id de la empresa no puede ser nulo")
                              Integer idCompany,
                              @NotNull(message = "Dirección de la sede no puede ser nulo")
                              @Size(min = 3, max = 100, message = "Nombre de la empresa debe tener entre 2 y 100 caracteres")
                              String name,
                              boolean enabled){}
