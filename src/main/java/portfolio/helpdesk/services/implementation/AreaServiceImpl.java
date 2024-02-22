package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.repositories.IAreaRepo;
import portfolio.helpdesk.services.IAreaService;

@RequiredArgsConstructor
@Service
public class AreaServiceImpl extends CrudImpl<Area, Integer> implements IAreaService {

    private final IAreaRepo areaRepo;

    @Override
    protected IAreaRepo getRepo() {
        return areaRepo;
    }

    @Override
    public void findAreaByNameAndIdCampus(String name, Integer idCampus) {
        getRepo().findAreaByNameAndIdCampus(name, idCampus).ifPresent(campus -> {
            throw new ModelAlreadyExistsException("Area with name " + name + " already exists in campus with id " + idCampus);
        });
    }
}
