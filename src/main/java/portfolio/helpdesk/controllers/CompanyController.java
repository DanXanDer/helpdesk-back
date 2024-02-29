package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.CompanyCreationDTO;
import portfolio.helpdesk.DTO.request.CompanyNameUpdateDTO;
import portfolio.helpdesk.DTO.response.CompanyResponseDTO;
import portfolio.helpdesk.mappers.CompanyMapper;
import portfolio.helpdesk.models.Company;
import portfolio.helpdesk.services.ICompanyService;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final ICompanyService companyService;
    private final CompanyMapper companyMapper = CompanyMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CompanyCreationDTO companyCreationDTO) {
        companyService.findCompanyByName(companyCreationDTO.name());
        Company company = companyService.save(companyMapper.convertToEntity(companyCreationDTO));
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
            @RequestBody @Valid CompanyNameUpdateDTO companyNameUpdateDTO) {
        String name = companyNameUpdateDTO.name();
        companyService.findCompanyByName(name);
        companyService.updateCompanyNameByIdCompany(idCompany, name);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PutMapping("/{idCompany}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable("idCompany") Integer idCompany) {
        Company company = companyService.findById(idCompany);
        boolean newStatus = !company.isEnabled();
        company.setEnabled(newStatus);
        company.getBranches().forEach(branch -> {
            branch.setEnabled(newStatus);
            branch.getAreas().forEach(area -> area.setEnabled(newStatus));
        });
        companyService.update(company);
        return ResponseEntity.ok().build();
    }
}
