package portfolio.helpdesk.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import portfolio.helpdesk.models.Client;

import java.util.List;

@Repository
public interface IClientRepo extends IGenericRepo<Client, Integer> {

    @Query("SELECT c FROM Client c WHERE c.area.idArea = :idArea")
    List<Client> findAllByArea(Integer idArea);
}
