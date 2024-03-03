package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Area;

public interface IAreaService extends ICRUD<Area, Integer> {
    void findByNameAndIdBranch(String name, Integer idBranch);

    void updateStatusByIdArea(Integer idArea, boolean status);

}
