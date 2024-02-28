package portfolio.helpdesk.DTO.response;

import lombok.Data;

import java.util.List;

public record CompanyResponseDTO(Integer idCompany, String name, boolean enabled, List<CampusResponseDTO> campus) {}
