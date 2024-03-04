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
        companyService.findByName(companyCreationDTO.name());
        CompanyResponseDTO company = companyMapper.convertToDTO(
                companyService.save(companyMapper.convertToEntity(companyCreationDTO))
        );
        URI location = URI.create(String.format("/company/%d", company.idCompany()));
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
        companyService.findByName(companyNameUpdateDTO.name());
        companyService.updateNameByIdCompany(idCompany, companyNameUpdateDTO.name());
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PutMapping("/{idCompany}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable("idCompany") Integer idCompany) {
        companyService.updateStatus(idCompany);
        return ResponseEntity.ok().build();
    }
}
