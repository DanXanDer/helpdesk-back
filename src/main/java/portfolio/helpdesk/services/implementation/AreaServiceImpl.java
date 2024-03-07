package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.DTO.request.AreaCreationDTO;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.exceptions.ModelNotFoundException;
import portfolio.helpdesk.mappers.AreaMapper;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.repositories.IAreaRepo;
import portfolio.helpdesk.services.IAreaService;
@RequiredArgsConstructor
@Service
public class AreaServiceImpl extends CrudImpl<Area, Integer> implements IAreaService {

    private final IAreaRepo areaRepo;
    private final AreaMapper areaMapper = AreaMapper.INSTANCE;

    @Override
    protected IAreaRepo getRepo() {
        return areaRepo;
    }

    @Override
    public void updateStatusByIdArea(Integer idArea) {
        Area area = getRepo().findById(idArea).orElseThrow(() -> new ModelNotFoundException("No se encontró el área"));
        boolean newStatus = !area.isEnabled();
        getRepo().updateStatusByIdArea(idArea, newStatus);
    }

    @Override
    public Area save(AreaCreationDTO areaCreationDTO) {
        getRepo().findByNameAndIdBranch(areaCreationDTO.name(), areaCreationDTO.idBranch()).ifPresent(branch -> {
            throw new ModelAlreadyExistsException("Area con este nombre ya existe.");
        });
        return getRepo().save(areaMapper.convertToEntity(areaCreationDTO));
    }

}
