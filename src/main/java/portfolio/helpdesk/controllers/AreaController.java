package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.AreaRequestDTO;
import portfolio.helpdesk.mappers.AreaMapper;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.services.IAreaService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/area")
public class AreaController {
    private final IAreaService areaService;
    private final AreaMapper areaMapper = AreaMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody AreaRequestDTO areaRequestDTO) {
        areaService.findAreaByNameAndIdCampus(areaRequestDTO.name(), areaRequestDTO.idCampus());
        Area area = areaService.save(areaMapper.convertToEntity(areaRequestDTO));
        URI location = URI.create(String.format("/area/%d", area.getIdArea()));
        return ResponseEntity.created(location).build();
    }

    @Transactional
    @PatchMapping("/{idArea}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable("idArea") Integer idArea
    ) {
        boolean newStatus = !areaService.findById(idArea).isEnabled();
        areaService.updateAreaStatusByIdArea(idArea, newStatus);
        return ResponseEntity.ok().build();
    }
}
