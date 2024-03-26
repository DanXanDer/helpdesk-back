package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.CompanyRequestDTO;
import portfolio.helpdesk.DTO.request.CompanyUpdateDTO;
import portfolio.helpdesk.DTO.response.BranchInfoResponseDTO;
import portfolio.helpdesk.DTO.response.BranchResponseDTO;
import portfolio.helpdesk.DTO.response.CompanyResponseDTO;
import portfolio.helpdesk.mappers.BranchMapper;
import portfolio.helpdesk.mappers.CompanyMapper;
import portfolio.helpdesk.mappers.CycleAvoidingMappingContext;
import portfolio.helpdesk.models.Company;
import portfolio.helpdesk.services.IBranchService;
import portfolio.helpdesk.services.ICompanyService;

import java.net.URI;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    private final ICompanyService companyService;
    private final IBranchService branchService;
    private final CompanyMapper companyMapper;
    private final BranchMapper branchMapper;

    @PostMapping
    public ResponseEntity<Company> save(@Valid @RequestBody CompanyRequestDTO companyRequestDTO) {
        companyService.validateNameExistence(companyRequestDTO.getName());
        Integer idCompany = companyService.save(companyMapper.convertToEntity(companyRequestDTO, new CycleAvoidingMappingContext())).getIdCompany();
        URI location = URI.create(String.format("/company/%d", idCompany));
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(value = "enabled", required = false) Boolean enabled) {
        List<?> companies;
        if (enabled == null) {
            companies = companyService.findAll(null)
                    .stream()
                    .map(company -> {
                        CompanyResponseDTO companyResponseDTO = companyMapper.convertToDTO(company, new CycleAvoidingMappingContext());
                        return Map.of(
                                "id", company.getIdCompany(),
                                "name", companyResponseDTO.getName(),
                                "enabled", companyResponseDTO.isEnabled(),
                                "branchCount", companyResponseDTO.getBranches().size(),
                                "areasCount", companyResponseDTO.getBranches()
                                        .stream()
                                        .mapToInt(branch -> branch.getAreas().size())
                                        .sum(),
                                "clientsCount", companyResponseDTO.getBranches()
                                        .stream()
                                        .mapToInt(branch -> branch.getAreas()
                                                .stream()
                                                .mapToInt(area -> area.getClients() == null ? 0 : area.getClients().size())
                                                .sum())
                                        .sum()
                        );
                    })
                    .sorted(Comparator.comparing(company -> (String) company.get("name")))
                    .toList();
        } else {
            companies = companyService.findAll(enabled).stream().map(companyMapper::convertToDTO).toList();
        }
        return ResponseEntity.ok(companies);
    }


    @PatchMapping("/{idCompany}/update")
    public ResponseEntity<Void> update(@PathVariable("idCompany") Integer idCompany, @RequestBody @Valid CompanyUpdateDTO companyUpdateDTO) {
        Company company = companyService.findById(idCompany);
        if (companyUpdateDTO.name() != null) {
            companyService.validateNameExistence(companyUpdateDTO.name());
        }
        companyMapper.updateFromDTO(companyUpdateDTO, company);
        if (companyUpdateDTO.enabled() != null) {
            branchService.updateStatusByCompany(idCompany, companyUpdateDTO.enabled());
        }
        companyService.save(company);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idCompany}")
    public ResponseEntity<?> getBranchesByCompany(
            @PathVariable("idCompany") Integer idCompany,
            @RequestParam(value = "enabled", required = false) Boolean enabled
    ) {
        if (enabled == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("company", companyService.findById(idCompany).getName());
            List<?> branches = branchService
                    .findAllByCompany(idCompany, null)
                    .stream()
                    .map(branch -> {
                        BranchResponseDTO branchResponseDTO = branchMapper.convertToDTO(branch, new CycleAvoidingMappingContext());
                        return Map.of(
                                "id", branch.getIdBranch(),
                                "name", branchResponseDTO.getName(),
                                "enabled", branchResponseDTO.isEnabled(),
                                "areaCount", branchResponseDTO.getAreas().size(),
                                "clientsCount", branchResponseDTO.getAreas()
                                        .stream()
                                        .mapToInt(area -> area.getClients() == null ? 0 : area.getClients().size())
                                        .sum()
                        );
                    })
                    .sorted(Comparator.comparing(company -> (String) company.get("name")))
                    .toList();
            response.put("branches", branches);
            return ResponseEntity.ok(response);
        } else {
            List<BranchInfoResponseDTO> response = branchService
                    .findAllByCompany(idCompany, enabled)
                    .stream()
                    .map(branchMapper::convertToDTO)
                    .toList();
            return ResponseEntity.ok(response);
        }
    }
}
