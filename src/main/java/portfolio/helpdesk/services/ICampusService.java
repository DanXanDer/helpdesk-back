package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Campus;

import java.util.List;

public interface ICampusService extends ICRUD<Campus, Integer> {
    void findCampusByNameAndCompany(String name, Integer idCompany);
    void updateCampusStatusByCompanyStatus(Integer idCompany ,boolean status);
    void updateCampusStatusByIdCampus(Integer idCampus, boolean status);
    List<Campus> findAllCampusByIdCompany(Integer idCompany);
}
