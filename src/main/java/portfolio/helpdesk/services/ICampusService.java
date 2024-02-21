package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Campus;

public interface ICampusService extends ICRUD<Campus, Integer> {
    void findCampusByNameAndCompany(String name, Integer idCompany);
    Campus updateCampus(Campus campus);
}
