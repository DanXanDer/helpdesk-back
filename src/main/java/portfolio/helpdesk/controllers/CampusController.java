package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.CampusDTO;
import portfolio.helpdesk.mappers.CampusMapper;
import portfolio.helpdesk.models.Campus;
import portfolio.helpdesk.services.ICampusService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/campus")
public class CampusController {
    private final ICampusService campusService;
    private final CampusMapper campusMapper = CampusMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CampusDTO campusDTO) {
        campusService.findCampusByNameAndCompany(campusDTO.getName(), campusDTO.getIdCompany());
        campusDTO.setEnabled(true);
        Campus campus = campusService.save(campusMapper.convertToEntity(campusDTO));
        URI location = URI.create(String.format("/campus/%d", campus.getIdCampus()));
        return ResponseEntity.created(location).build();
    }


    @GetMapping("/company/{idCompany}")
    public ResponseEntity<List<CampusDTO>> findAllByIdCompany(@PathVariable("idCompany") Integer idCompany){
        List<CampusDTO> campusList = campusService.findAllCampusByIdCompany(idCompany).stream().map(campusMapper::convertToDTO).toList();
        return ResponseEntity.ok(campusList);
    }

    @PatchMapping("/{idCampus}/status")
    @Transactional
    public ResponseEntity<CampusDTO> updateStatus(
            @PathVariable("idCampus") Integer idCampus){
        boolean status = campusService.findById(idCampus).isEnabled();
        campusService.updateCampusStatusByIdCampus(idCampus, !status);
        return ResponseEntity.ok().build();
    }

}
