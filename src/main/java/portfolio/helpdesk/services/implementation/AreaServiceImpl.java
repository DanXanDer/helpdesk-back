package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.repositories.IAreaRepo;
import portfolio.helpdesk.services.IAreaService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AreaServiceImpl extends CrudImpl<Area, Integer> implements IAreaService {

    private final IAreaRepo areaRepo;

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
        return getRepo().findAllByBranch(idBranch, enabled).orElseThrow(() -> new ModelAlreadyExistsException("No se encontraron areas para esta sucursal"));
    }

    @Override
    public void updateStatusByBranch(Integer idBranch, boolean enabled) {
        List<Area> areas = getRepo().findAllByBranch(idBranch, !enabled).orElseThrow(() -> new ModelAlreadyExistsException("No se encontraron areas para esta sucursal"));
        areas.forEach(area -> area.setEnabled(enabled));
        //actualizar estado de usuarios
        getRepo().saveAll(areas);
    }

}
