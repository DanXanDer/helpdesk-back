package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.helpdesk.DTO.CampusDTO;
import portfolio.helpdesk.mappers.CampusMapper;
import portfolio.helpdesk.models.Campus;
import portfolio.helpdesk.services.ICampusService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/campus")
public class CampusController {
    private final ICampusService campusService;
    private final CampusMapper campusMapper = CampusMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CampusDTO campusDTO) {
        campusService.findCampusByNameAndCompany(campusDTO.getName(), campusDTO.getIdCompany());
        Campus campus = campusService.save(campusMapper.convertToEntity(campusDTO));
        URI location = URI.create(String.format("/campus/%d", campus.getIdCampus()));
        return ResponseEntity.created(location).build();
    }
}
