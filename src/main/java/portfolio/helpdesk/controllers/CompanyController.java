package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.CompanyCreationDTO;
import portfolio.helpdesk.DTO.request.CompanyUpdateDTO;
import portfolio.helpdesk.DTO.response.CompanyResponse;
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
        CompanyResponse company = companyMapper.convertToDTO(companyService.save(companyCreationDTO));
        URI location = URI.create(String.format("/company/%d", company.idCompany()));
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<Set<CompanyResponse>> findAll() {
        Set<CompanyResponse> companyList = companyService.findAll().stream().map(companyMapper::convertToDTO).collect(Collectors.toSet());
        return ResponseEntity.ok(companyList);
    }

    @PatchMapping("/name-update")
    public ResponseEntity<Void> updateName(@RequestBody @Valid CompanyUpdateDTO companyUpdateDTO) {
        companyService.updateNameByIdCompany(companyUpdateDTO);
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
