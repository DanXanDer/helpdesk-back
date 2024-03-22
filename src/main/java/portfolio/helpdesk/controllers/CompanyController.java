package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.CompanyRequestDTO;
import portfolio.helpdesk.DTO.request.CompanyUpdateDTO;
import portfolio.helpdesk.DTO.response.BranchResponseDTO;
import portfolio.helpdesk.mappers.BranchMapper;
import portfolio.helpdesk.mappers.CompanyMapper;
import portfolio.helpdesk.mappers.CycleAvoidingMappingContext;
import portfolio.helpdesk.models.Company;
import portfolio.helpdesk.services.IBranchService;
import portfolio.helpdesk.services.ICompanyService;

import java.net.URI;
import java.util.List;

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


    /* @GetMapping
     public ResponseEntity<List<Map<String, Object>>> findAll(@RequestParam(value = "enabled", required = false) Boolean enabled) {
         if (enabled != null) {
             List<Map<String, Object>> companyList = companyService
                     .findAll(enabled)
                     .stream()
                     .map(company -> )
         }

         *//*List<Map<String, Object>> companyList = companyService
                .findAll(enabled)
                .stream()
                .map(company -> {
                    CompanyResponseDTO companyResponseDTO = companyMapper.convertToDTO(company, new CycleAvoidingMappingContext());
                    Map<String, Object> companyResponse = new HashMap<>(Map.of(
                            "idCompany", company.getIdCompany(),
                            "name", companyResponseDTO.getName()
                    ));
                    if (enabled == null) {
                        companyResponse.put("enabled", companyResponseDTO.isEnabled());
                        companyResponse.put("branchCount", companyResponseDTO.getBranches().size());
                        companyResponse.put("areasCount",
                                companyResponseDTO
                                        .getBranches()
                                        .stream()
                                        .mapToInt(branch -> branch.getAreas().size())
                                        .reduce(0, Integer::sum)
                        );
                    }
                    return companyResponse;
                }).toList();*//*
        return ResponseEntity.ok(companyList);
    }
*/
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
    public ResponseEntity<List<BranchResponseDTO>> getBranchesByCompany(
            @PathVariable("idCompany") Integer idCompany,
            @RequestParam(value = "enabled", required = false) Boolean enabled
    ) {
        companyService.getReferenceById(idCompany);
        return ResponseEntity.ok(branchService
                .findAllByCompany(idCompany, enabled)
                .stream()
                .map(branch -> branchMapper.convertToDTO(branch, new CycleAvoidingMappingContext()))
                .toList());
    }

}
