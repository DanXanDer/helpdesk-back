package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.exceptions.ModelNotFoundException;
import portfolio.helpdesk.models.Branch;
import portfolio.helpdesk.repositories.IBranchRepo;
import portfolio.helpdesk.services.IAreaService;
import portfolio.helpdesk.services.IBranchService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BranchServiceImpl extends CrudImpl<Branch, Integer> implements IBranchService {

    private final IBranchRepo branchRepo;
    private final IAreaService areaService;

    @Override
    protected IBranchRepo getRepo() {
        return branchRepo;
    }

    @Override
    public void findByNameAndCompany(String name, Integer idCompany) {
        getRepo().findByNameAndCompany(name, idCompany).ifPresent(branch -> {
            throw new ModelAlreadyExistsException("Sucursal con este nombre ya existe.");
        });
    }

    @Override
    public void updateStatusByCompany(Integer idCompany, Boolean enabled) {
        List<Branch> branches = getRepo().findAllByCompany(idCompany, !enabled).orElseThrow(() -> new ModelNotFoundException("No se encontraron sucursales"));
        branches.forEach(branch -> {
            branch.setEnabled(enabled);
            areaService.updateStatusByBranch(branch.getIdBranch(), enabled);
        });
        getRepo().saveAll(branches);
    }

    @Override
    public List<Branch> findAllByCompany(Integer idCompany, Boolean enabled) {
        return getRepo().findAllByCompany(idCompany, enabled).orElseThrow(() -> new ModelNotFoundException("No se encontraron sucursales"));
    }
}
