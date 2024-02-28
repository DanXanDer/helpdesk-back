package portfolio.helpdesk.DTO.response;

import lombok.Data;

public record AreaResponseDTO(Integer idArea, int idCampus, String name, boolean enabled){}
