package portfolio.helpdesk.services;

import portfolio.helpdesk.DTO.request.BranchCreationDTO;
import portfolio.helpdesk.models.Branch;

public interface IBranchService extends ICRUD<Branch, Integer> {
    void updateStatus(Integer idBranch);

    Branch save(BranchCreationDTO branchCreationDTO);

}
