package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.exceptions.ModelNotFoundException;
import portfolio.helpdesk.models.Campus;
import portfolio.helpdesk.repositories.ICampusRepo;
import portfolio.helpdesk.services.ICampusService;

@RequiredArgsConstructor
@Service
public class CampusServiceImpl extends CrudImpl<Campus, Integer> implements ICampusService {

    private final ICampusRepo campusRepo;

    @Override
    protected ICampusRepo getRepo() {
        return campusRepo;
    }

    @Override
    public void findCampusByNameAndCompany(String name, Integer idCompany) {
        getRepo().findCampusByNameAndCompany_IdCompany(name, idCompany).ifPresent(campus -> {
            throw new ModelAlreadyExistsException("Campus with name " + name + " already exists in company with id " + idCompany);
        });
    }

    @Override
    public void updateCampusStatusByCompanyStatus(Integer idCompany, boolean status) {
        getRepo().findAllIdCampusByIdCompany(idCompany).forEach(
                idCampus -> getRepo().updateCampusStatus(idCampus, status));
    }

    @Override
    public void updateCampusStatusByIdCampus(Integer idCampus, boolean status) {
        getRepo().findById(idCampus).orElseThrow(() -> new ModelNotFoundException(idCampus));
        getRepo().updateCampusStatus(idCampus, status);
    }

}
