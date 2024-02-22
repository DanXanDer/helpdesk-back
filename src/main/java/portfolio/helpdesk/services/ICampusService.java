package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Campus;

import java.util.List;

public interface ICampusService extends ICRUD<Campus, Integer> {
    void findCampusByNameAndCompany(String name, Integer idCompany);
    void updateCampusStatusByCompanyStatus(Integer idCompany ,boolean status);

    Campus updateCampus(Campus campus);
}
