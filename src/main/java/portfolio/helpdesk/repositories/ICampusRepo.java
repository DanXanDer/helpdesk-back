package portfolio.helpdesk.repositories;

import portfolio.helpdesk.models.Campus;

import java.util.Optional;

public interface ICampusRepo extends IGenericRepo<Campus, Integer> {
    Optional<Campus> findCampusByNameAndCompany_IdCompany(String name, Integer idCompany);
}
