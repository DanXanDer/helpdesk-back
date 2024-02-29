package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.BranchCreationDTO;
import portfolio.helpdesk.mappers.BranchMapper;
import portfolio.helpdesk.models.Branch;
import portfolio.helpdesk.services.IBranchService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {
    private final IBranchService branchService;
    private final BranchMapper branchMapper = BranchMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<Void> saveBranch(@Valid @RequestBody BranchCreationDTO branchCreationDTO) {
        branchService.findBranchByNameAndCompany(branchCreationDTO.name(), branchCreationDTO.idCompany());
        Branch branch = branchService.save(branchMapper.convertToEntity(branchCreationDTO));
        URI location = URI.create(String.format("/branch/%d", branch.getIdBranch()));
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{idBranch}/status")
    @Transactional
    public ResponseEntity<Void> updateBranchStatus(
            @PathVariable("idBranch") Integer idBranch) {
        Branch branch = branchService.findById(idBranch);
        boolean newStatus = !branch.isEnabled();
        branch.setEnabled(newStatus);
        branch.getAreas().forEach(area -> area.setEnabled(newStatus));
        branchService.save(branch);
        return ResponseEntity.ok().build();
    }

}
