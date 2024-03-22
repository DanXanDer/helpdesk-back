package portfolio.helpdesk.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import portfolio.helpdesk.DTO.RoleDTO;
import portfolio.helpdesk.DTO.response.CompanyInfoResponseDTO;
import portfolio.helpdesk.DTO.response.CompanyResponseDTO;
import portfolio.helpdesk.mappers.CompanyMapper;
import portfolio.helpdesk.mappers.CycleAvoidingMappingContext;
import portfolio.helpdesk.mappers.RoleMapper;
import portfolio.helpdesk.services.ICompanyService;
import portfolio.helpdesk.services.IRoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {
    private final IRoleService roleService;
    private final ICompanyService companyService;
    private final RoleMapper roleMapper;
    private final CompanyMapper companyMapper;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> findRolesNoAdmin() {
        List<RoleDTO> roles = roleService.findRolesNoAdmin().stream().map(roleMapper::convertToDTO).toList();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/noAdmin/companies")
    public ResponseEntity<List<CompanyInfoResponseDTO>> findCompaniesNoAdmin() {
        List<CompanyInfoResponseDTO> companies = companyService.findForNoAdmin().stream().map(companyMapper::convertToDTO).toList();
        return ResponseEntity.ok(companies);
    }

    public ResponseEntity<List<Map<String, Object>>> findAll(@RequestParam(value = "enabled", required = false) Boolean enabled) {
        List<Map<String, Object>> companyList = companyService
                .findAll(enabled)
                .stream()
                .map(company -> {
                    CompanyResponseDTO companyResponseDTO = companyMapper.convertToDTO(company, new CycleAvoidingMappingContext());
                    return (Map<String, Object>) new HashMap<String, Object>(Map.of(
                            "idCompany", company.getIdCompany(),
                            "name", companyResponseDTO.getName(),
                            "enabled", companyResponseDTO.isEnabled(),
                            "branchCount", companyResponseDTO.getBranches().size(),
                            "areasCount", companyResponseDTO
                                    .getBranches()
                                    .stream()
                                    .mapToInt(branch -> branch.getAreas().size())
                                    .reduce(0, Integer::sum)
                    ));
                }).toList();
        return ResponseEntity.ok(companyList);
    }

}
