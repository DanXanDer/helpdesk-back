package portfolio.helpdesk.services;

import portfolio.helpdesk.DTO.request.AreaCreationDTO;
import portfolio.helpdesk.models.Area;

public interface IAreaService extends ICRUD<Area, Integer> {
    void updateStatusByIdArea(Integer idArea);

    Area save(AreaCreationDTO areaCreationDTO);

}
