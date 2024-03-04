package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.exceptions.ModelNotFoundException;
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
    public void findByNameAndIdBranch(String name, Integer idBranch) {
        getRepo().findByNameAndIdBranch(name, idBranch).ifPresent(branch -> {
            throw new ModelAlreadyExistsException("Area with name " + name + " already exists in branch with id " + idBranch);
        });
    }

    @Override
    public void updateStatusByIdArea(Integer idArea) {
        Area area = getRepo().findById(idArea).orElseThrow(ModelNotFoundException::new);
        boolean newStatus = !area.isEnabled();
        getRepo().updateStatusByIdArea(idArea, newStatus);
    }

}
