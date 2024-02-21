package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.CompanyDTO;
import portfolio.helpdesk.mappers.CompanyMapper;
import portfolio.helpdesk.models.Company;
import portfolio.helpdesk.services.ICompanyService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final ICompanyService companyService;
    private final CompanyMapper companyMapper = CompanyMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CompanyDTO companyDTO) {
        companyService.findCompanyByName(companyDTO.getName());
        Company company = companyService.save(companyMapper.convertToEntity(companyDTO));
        URI location = URI.create(String.format("/company/%d", company.getIdCompany()));
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> findAll() {
        List<CompanyDTO> list = companyService.findAll().stream().map(companyMapper::convertToDTO).toList();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody CompanyDTO companyDTO) {
        companyService.findCompanyByName(companyDTO.getName());
        companyDTO.setIdCompany(id);
        Company company = companyService.update(id, companyMapper.convertToEntity(companyDTO));
        return ResponseEntity.ok().body(companyMapper.convertToDTO(company));
    }
}
