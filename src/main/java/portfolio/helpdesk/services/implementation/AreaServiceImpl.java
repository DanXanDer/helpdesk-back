package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.exceptions.ModelNotFoundException;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.repositories.IAreaRepo;
import portfolio.helpdesk.services.IAreaService;
import portfolio.helpdesk.services.IClientService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AreaServiceImpl extends CrudImpl<Area, Integer> implements IAreaService {

    private final IAreaRepo areaRepo;
    private final IClientService clientService;

    @Override
    protected IAreaRepo getRepo() {
        return areaRepo;
    }

    @Override
    public void findByNameAndBranch(String name, Integer idBranch) {
        getRepo().findByNameAndBranch(name, idBranch).ifPresent(branch -> {
            throw new ModelAlreadyExistsException("Area con este nombre ya existe.");
        });
    }

    @Override
    public List<Area> findAllByBranch(Integer idBranch, Boolean enabled) {
        return getRepo().findAllByBranch(idBranch, enabled).orElseThrow(() -> new ModelNotFoundException("No se encontraron areas para esta sucursal"));
    }

    @Override
    public void updateStatusByBranch(Integer idBranch, boolean enabled) {
        List<Area> areas = getRepo().findAllByBranch(idBranch, null).orElseThrow(() -> new ModelNotFoundException("No se encontraron areas para esta sucursal"));
        areas.forEach(area -> {
            area.setEnabled(enabled);
            clientService.updateStatusByArea(area.getIdArea(), enabled);
        });
        getRepo().saveAll(areas);
    }
}
