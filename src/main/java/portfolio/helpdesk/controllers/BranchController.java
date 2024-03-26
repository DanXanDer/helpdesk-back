package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.BranchRequestDTO;
import portfolio.helpdesk.DTO.request.BranchUpdateDTO;
import portfolio.helpdesk.mappers.AreaMapper;
import portfolio.helpdesk.mappers.BranchMapper;
import portfolio.helpdesk.mappers.CycleAvoidingMappingContext;
import portfolio.helpdesk.models.Branch;
import portfolio.helpdesk.services.IAreaService;
import portfolio.helpdesk.services.IBranchService;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
        branchService.findByNameAndCompany(branchRequestDTO.getName(), branchRequestDTO.getCompany().getIdCompany(), null);
        Integer idBranch = branchService.save(branchMapper.convertToEntity(branchRequestDTO, new CycleAvoidingMappingContext())).getIdBranch();
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
        if (branchUpdateDTO.getName() != null) {
            branchService.findByNameAndCompany(branchUpdateDTO.getName(), branch.getCompany().getIdCompany(), idBranch);
        }
        branchMapper.updateFromDTO(branchUpdateDTO, branch);
        if (branchUpdateDTO.getEnabled() != null) {
            areaService.updateStatusByBranch(idBranch, branchUpdateDTO.getEnabled());
        }
        branchService.save(branch);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idBranch}/areas")
    public ResponseEntity<?> getAreasByBranch(
            @PathVariable("idBranch") Integer idBranch,
            @RequestParam(value = "enabled", required = false) Boolean enabled) {
        branchService.getReferenceById(idBranch);
        List<?> areas;
        if (enabled == null) {
            areas = areaService
                    .findAllByBranch(idBranch, null)
                    .stream()
                    .map(area -> Map.of(
                            "id", area.getIdArea(),
                            "name", area.getName(),
                            "enabled", area.isEnabled(),
                            "clientsCount", area.getClients().size()
                    ))
                    .sorted(Comparator.comparing(company -> (String) company.get("name")))
                    .toList();

        } else {
            areas = areaService
                    .findAllByBranch(idBranch, enabled)
                    .stream().map(areaMapper::convertToDTO)
                    .toList();
        }
        return ResponseEntity.ok(areas);
    }
}
