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
        Integer idCompany = companyService.save(companyMapper.convertToEntity(companyRequestDTO)).getIdCompany();
        URI location = URI.create(String.format("/company/%d", idCompany));
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponseDTO>> findAll(@RequestParam(value = "enabled", required = false) Boolean enabled) {
        List<CompanyResponseDTO> companyList = companyService.findAll(enabled).stream().map(companyMapper::convertToDTO).toList();
        return ResponseEntity.ok(companyList);
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

    @GetMapping("/{idCompany}/branches")
    public ResponseEntity<List<BranchResponseDTO>> getBranchesByCompany(
            @PathVariable("idCompany") Integer idCompany,
            @RequestParam(value = "enabled", required = false) Boolean enabled
    ) {
        companyService.getReferenceById(idCompany);
        return ResponseEntity.ok(branchService.findAllByCompany(idCompany, enabled).stream().map(branchMapper::convertToDTO).toList());
    }

}
