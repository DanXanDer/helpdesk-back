package portfolio.helpdesk.DTO.response;

import lombok.Data;

import java.util.List;

public record CampusResponseDTO(Integer idCampus, Integer idCompany, String name, boolean enabled, List<AreaResponseDTO> areas) {}
