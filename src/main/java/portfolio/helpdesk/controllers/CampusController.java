package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.CampusRequestDTO;
import portfolio.helpdesk.mappers.CampusMapper;
import portfolio.helpdesk.models.Campus;
import portfolio.helpdesk.services.IAreaService;
import portfolio.helpdesk.services.ICampusService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/campus")
public class CampusController {
    private final ICampusService campusService;
    private final IAreaService areaService;
    private final CampusMapper campusMapper = CampusMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<Void> saveCampus(@Valid @RequestBody CampusRequestDTO campusRequestDTO) {
        campusService.findCampusByNameAndCompany(campusRequestDTO.name(), campusRequestDTO.idCompany());
        Campus campus = campusService.save(campusMapper.convertToEntity(campusRequestDTO));
        URI location = URI.create(String.format("/campus/%d", campus.getIdCampus()));
        return ResponseEntity.created(location).build();
    }


    @PatchMapping("/{idCampus}/status")
    @Transactional
    public ResponseEntity<Void> updateCampusStatus(
            @PathVariable("idCampus") Integer idCampus) {
        boolean newStatus = !campusService.findById(idCampus).isEnabled();
        campusService.updateCampusStatusByIdCampus(idCampus, newStatus);
        areaService.updateAreaStatusByCampusStatus(idCampus, newStatus);
        return ResponseEntity.ok().build();
    }

}
