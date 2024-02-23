package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Area;

import java.util.List;

public interface IAreaService extends ICRUD<Area, Integer> {
    void findAreaByNameAndIdCampus(String name, Integer idCampus);
    void updateAreaStatusByIdArea(Integer idArea, boolean status);
    List<Area> findAllAreasByIdCampus(Integer idCampus);
}
