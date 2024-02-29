package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Area;

import java.util.List;

public interface IAreaService extends ICRUD<Area, Integer> {
    void findAreaByNameAndIdBranch(String name, Integer idBranch);
    void updateAreaStatusByIdArea(Integer idArea, boolean status);

    void updateAreaStatusByBranchStatus(Integer idBranch, boolean status);

    List<Area> findAllAreasByIdBranch(Integer idBranch);
}
