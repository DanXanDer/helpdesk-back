package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Area;

import java.util.List;

public interface IAreaService extends ICRUD<Area, Integer> {
    void findByNameAndBranch(String name, Integer idBranch);

    List<Area> findAllByBranch(Integer idBranch, Boolean enabled);

    void updateStatusByBranch(Integer idBranch, boolean enabled);
}
