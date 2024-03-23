package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.CompanyRequestDTO;
import portfolio.helpdesk.DTO.request.CompanyUpdateDTO;
import portfolio.helpdesk.DTO.response.BranchResponseDTO;
import portfolio.helpdesk.DTO.response.CompanyResponseDTO;
import portfolio.helpdesk.mappers.BranchMapper;
import portfolio.helpdesk.mappers.CompanyMapper;
import portfolio.helpdesk.mappers.CycleAvoidingMappingContext;
import portfolio.helpdesk.models.Company;
import portfolio.helpdesk.services.IBranchService;
import portfolio.helpdesk.services.ICompanyService;

import java.net.URI;
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
                        Map<String, Object> companyInfo = new HashMap<>();
                        companyInfo.put("idCompany", company.getIdCompany());
                        companyInfo.put("name", companyResponseDTO.getName());
                        companyInfo.put("enabled", companyResponseDTO.isEnabled());
                        companyInfo.put("branchCount", companyResponseDTO.getBranches().size());
                        companyInfo.put("areasCount", companyResponseDTO.getBranches()
                                .stream()
                                .mapToInt(branch -> branch.getAreas().size())
                                .sum());
                        return companyInfo;
                    })
                    .toList();
        } else {
            companies = companyService.findAll(enabled).stream().map(companyMapper::convertToDTO).toList();
        }
        return ResponseEntity.ok(companies);
    }


    @PatchMapping("/{idCompany}/update")
    public ResponseEntity<Void> update(@PathVariable("idCompany") Integer idCompany, @RequestBody @Valid CompanyUpdateDTO companyUpdateDTO) {
        Company company = companyService.findById(idCompany);
        if (companyUpdateDTO.getName() != null) {
            companyService.validateNameExistence(companyUpdateDTO.getName());
        }
        companyMapper.updateFromDTO(companyUpdateDTO, company);
        if (companyUpdateDTO.getEnabled() != null) {
            branchService.updateStatusByCompany(idCompany, companyUpdateDTO.getEnabled());
        }
        companyService.save(company);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idCompany}/branches")
    public ResponseEntity<?> getBranchesByCompany(
            @PathVariable("idCompany") Integer idCompany,
            @RequestParam(value = "enabled", required = false) Boolean enabled
    ) {
        List<?> branches;
        if (enabled == null) {
            branches = branchService
                    .findAllByCompany(idCompany, null)
                    .stream()
                    .map(branch -> {
                        BranchResponseDTO branchResponseDTO = branchMapper.convertToDTO(branch, new CycleAvoidingMappingContext());
                        Map<String, Object> branchInfo = new HashMap<>();
                        branchInfo.put("idBranch", branch.getIdBranch());
                        branchInfo.put("name", branchResponseDTO.getName());
                        branchInfo.put("enabled", branchResponseDTO.isEnabled());
                        branchInfo.put("areaCount", branchResponseDTO.getAreas().size());
                        return branchInfo;
                    })
                    .toList();
        } else {
            branches = branchService
                    .findAllByCompany(idCompany, enabled)
                    .stream()
                    .map(branchMapper::convertToDTO)
                    .toList();
        }
        return ResponseEntity.ok(branches);
    }

}
