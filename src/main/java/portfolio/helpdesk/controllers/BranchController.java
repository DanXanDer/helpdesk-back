package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.BranchRequestDTO;
import portfolio.helpdesk.DTO.request.BranchUpdateDTO;
import portfolio.helpdesk.DTO.response.AreaResponseDTO;
import portfolio.helpdesk.mappers.AreaMapper;
import portfolio.helpdesk.mappers.BranchMapper;
import portfolio.helpdesk.models.Branch;
import portfolio.helpdesk.services.IAreaService;
import portfolio.helpdesk.services.IBranchService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {
    private final IBranchService branchService;
    private final IAreaService areaService;
    private final BranchMapper branchMapper;
    private final AreaMapper areaMapper;

    @PostMapping
    public ResponseEntity<Void> saveBranch(@Valid @RequestBody BranchRequestDTO branchRequestDTO) {
        branchService.findByNameAndCompany(branchRequestDTO.getName(), branchRequestDTO.getIdCompany());
        Integer idBranch = branchService.save(branchMapper.convertToEntity(branchRequestDTO)).getIdBranch();
        URI location = URI.create(String.format("/branch/%d", idBranch));
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{idBranch}/update")
    @Transactional
    public ResponseEntity<Void> updateBranchStatus(
            @PathVariable("idBranch") Integer idBranch,
            @Valid @RequestBody BranchUpdateDTO branchUpdateDTO
    ) {
        Branch branch = branchService.findById(idBranch);
        if (branchUpdateDTO.name() != null) {
            branchService.findByNameAndCompany(branchUpdateDTO.name(), branch.getCompany().getIdCompany());
        }
        branchMapper.updateFromDTO(branchUpdateDTO, branch);
        if (branchUpdateDTO.enabled() != null) {
            areaService.updateStatusByBranch(idBranch, branchUpdateDTO.enabled());
        }
        branchService.save(branch);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idBranch}/areas")
    public ResponseEntity<List<AreaResponseDTO>> getAreasByBranch(
            @PathVariable("idBranch") Integer idBranch,
            @RequestParam(value = "enabled", required = false) Boolean enabled) {
        branchService.getReferenceById(idBranch);
        return ResponseEntity.ok(areaService.findAllByBranch(idBranch, enabled).stream().map(areaMapper::convertToDTO).toList());
    }
}
