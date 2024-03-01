package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Area;

public interface IAreaService extends ICRUD<Area, Integer> {
    void findAreaByNameAndIdBranch(String name, Integer idBranch);
    void updateAreaStatusByIdArea(Integer idArea, boolean status);

}
