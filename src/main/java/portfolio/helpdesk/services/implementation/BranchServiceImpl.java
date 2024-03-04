package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.exceptions.ModelNotFoundException;
import portfolio.helpdesk.models.Branch;
import portfolio.helpdesk.repositories.IBranchRepo;
import portfolio.helpdesk.services.IBranchService;

@RequiredArgsConstructor
@Service
public class BranchServiceImpl extends CrudImpl<Branch, Integer> implements IBranchService {

    private final IBranchRepo branchRepo;

    @Override
    protected IBranchRepo getRepo() {
        return branchRepo;
    }

    @Override
    public void findByNameAndCompany(String name, Integer idCompany) {
        getRepo().findBranchByNameAndIdCompany(name, idCompany).ifPresent(branch -> {
            throw new ModelAlreadyExistsException("Branch with name " + name + " already exists in company with id " + idCompany);
        });
    }

    @Override
    public void updateStatus(Integer idBranch) {
        Branch branch = getRepo().findById(idBranch).orElseThrow(ModelNotFoundException::new);
        boolean newStatus = !branch.isEnabled();
        branch.setEnabled(newStatus);
        branch.getAreas().forEach(area -> area.setEnabled(newStatus));
        getRepo().save(branch);
    }

}
