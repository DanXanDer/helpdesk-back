package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Area;

public interface IAreaService extends ICRUD<Area, Integer> {
    void findAreaByNameAndIdCampus(String name, Integer idCampus);
}
