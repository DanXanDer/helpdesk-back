package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.BranchCreationDTO;
import portfolio.helpdesk.DTO.response.BranchResponseDTO;
import portfolio.helpdesk.mappers.BranchMapper;
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
        branchService.findByNameAndCompany(branchCreationDTO.name(), branchCreationDTO.idCompany());
        BranchResponseDTO branch = branchMapper
                .convertToDTO(branchService.save(branchMapper.convertToEntity(branchCreationDTO)));
        URI location = URI.create(String.format("/branch/%d", branch.idBranch()));
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{idBranch}/status")
    @Transactional
    public ResponseEntity<BranchResponseDTO> updateBranchStatus(
            @PathVariable("idBranch") Integer idBranch) {
        branchService.updateStatus(idBranch);
        return ResponseEntity.ok().build();
    }

}
