package portfolio.helpdesk.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portfolio.helpdesk.exceptions.ModelAlreadyExistsException;
import portfolio.helpdesk.exceptions.ModelNotFoundException;
import portfolio.helpdesk.models.Branch;
import portfolio.helpdesk.repositories.IBranchRepo;
import portfolio.helpdesk.services.IBranchService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BranchServiceImpl extends CrudImpl<Branch, Integer> implements IBranchService {

    private final IBranchRepo branchRepo;

    @Override
    protected IBranchRepo getRepo() {
        return branchRepo;
    }

    @Override
    public void findBranchByNameAndCompany(String name, Integer idCompany) {
        getRepo().findBranchByNameAndCompany_IdCompany(name, idCompany).ifPresent(branch -> {
            throw new ModelAlreadyExistsException("Branch with name " + name + " already exists in company with id " + idCompany);
        });
    }

    @Override
    public void updateBranchStatusByCompanyStatus(Integer idCompany, boolean status) {
        getRepo().findAllBranchByIdCompany(idCompany).forEach(
                branch -> getRepo().updateBranchStatusByIdBranch(branch.getIdBranch(), status));
    }

    @Override
    public void updateBranchStatusByIdBranch(Integer idBranch, boolean status) {
        getRepo().findById(idBranch).orElseThrow(() -> new ModelNotFoundException(idBranch));
        getRepo().updateBranchStatusByIdBranch(idBranch, status);
    }

    @Override
    public List<Branch> findAllBranchByIdCompany(Integer idCompany) {
        return getRepo().findAllBranchByIdCompany(idCompany);
    }

}
