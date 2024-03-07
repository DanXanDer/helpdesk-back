package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.DTO.request.BranchCreationDTO;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.exceptions.ModelNotFoundException;
import portfolio.helpdesk.mappers.BranchMapper;
import portfolio.helpdesk.models.Branch;
import portfolio.helpdesk.repositories.IBranchRepo;
import portfolio.helpdesk.services.IBranchService;

@RequiredArgsConstructor
@Service
public class BranchServiceImpl extends CrudImpl<Branch, Integer> implements IBranchService {

    private final IBranchRepo branchRepo;
    private final BranchMapper branchMapper = BranchMapper.INSTANCE;

    @Override
    protected IBranchRepo getRepo() {
        return branchRepo;
    }

    @Override
    public void updateStatus(Integer idBranch) {
        Branch branch = getRepo().findById(idBranch).orElseThrow(() -> new ModelNotFoundException("No se encontró la sucursal"));
        boolean newStatus = !branch.isEnabled();
        branch.setEnabled(newStatus);
        branch.getAreas().forEach(area -> area.setEnabled(newStatus));
        getRepo().save(branch);
    }

    @Override
    public Branch save(BranchCreationDTO branchCreationDTO) {
        getRepo().findBranchByNameAndIdCompany(branchCreationDTO.name(), branchCreationDTO.idCompany()).ifPresent(branch -> {
            throw new ModelAlreadyExistsException("Sucursal con este nombre ya existe.");
        });
        return getRepo().save(branchMapper.convertToEntity(branchCreationDTO));
    }
}
