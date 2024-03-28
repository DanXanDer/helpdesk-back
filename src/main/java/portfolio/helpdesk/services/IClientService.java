package portfolio.helpdesk.services;

import portfolio.helpdesk.models.Client;

import java.util.List;

public interface IClientService extends ICRUD<Client, Integer> {

    List<Client> findAll();

    void updateStatusByArea(Integer idArea, boolean status);
}
