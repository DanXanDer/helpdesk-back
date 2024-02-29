package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.CompanyRequestDTO;
import portfolio.helpdesk.DTO.response.CompanyResponseDTO;
import portfolio.helpdesk.mappers.CompanyMapper;
import portfolio.helpdesk.models.Company;
import portfolio.helpdesk.services.IBranchService;
import portfolio.helpdesk.services.ICompanyService;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final ICompanyService companyService;
    private final IBranchService branchService;
    private final CompanyMapper companyMapper = CompanyMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CompanyRequestDTO companyRequestDTO) {
        companyService.findCompanyByName(companyRequestDTO.name());
        Company company = companyService.save(companyMapper.convertToEntity(companyRequestDTO));
        URI location = URI.create(String.format("/company/%d", company.getIdCompany()));
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<Set<CompanyResponseDTO>> findAll() {
        Set<CompanyResponseDTO> companyList = companyService.findAll().stream().map(companyMapper::convertToDTO).collect(Collectors.toSet());
        return ResponseEntity.ok(companyList);
    }

    @PatchMapping("/{idCompany}/name")
    public ResponseEntity<Void> updateName(
            @PathVariable("idCompany") Integer idCompany,
            @Valid @RequestBody CompanyRequestDTO companyRequestDTO) {
        companyService.updateCompanyNameByIdCompany(idCompany, companyRequestDTO.name());
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PatchMapping("/{idCompany}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable("idCompany") Integer idCompany) {
        boolean status = companyService.findById(idCompany).isEnabled();
        companyService.updateCompanyStatusByIdCompany(idCompany, !status);
        branchService.updateBranchStatusByCompanyStatus(idCompany, !status);
        return ResponseEntity.ok().build();
    }


}
