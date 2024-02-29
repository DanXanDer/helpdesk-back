package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.BranchRequestDTO;
import portfolio.helpdesk.mappers.BranchMapper;
import portfolio.helpdesk.models.Branch;
import portfolio.helpdesk.services.IAreaService;
import portfolio.helpdesk.services.IBranchService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {
    private final IBranchService branchService;
    private final IAreaService areaService;
    private final BranchMapper branchMapper = BranchMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<Void> saveBranch(@Valid @RequestBody BranchRequestDTO branchRequestDTO) {
        //branchService.findBranchByNameAndCompany(branchRequestDTO.name(), branchRequestDTO.idCompany());
        Branch branch = branchService.save(branchMapper.convertToEntity(branchRequestDTO));
        URI location = URI.create(String.format("/branch/%d", branch.getIdBranch()));
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{idBranch}/status")
    @Transactional
    public ResponseEntity<Void> updateBranchStatus(
            @PathVariable("idBranch") Integer idBranch) {
        boolean newStatus = !branchService.findById(idBranch).isEnabled();
        branchService.updateBranchStatusByIdBranch(idBranch, newStatus);
        areaService.updateAreaStatusByBranchStatus(idBranch, newStatus);
        return ResponseEntity.ok().build();
    }

}
