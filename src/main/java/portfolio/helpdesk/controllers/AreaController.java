package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.AreaDTO;
import portfolio.helpdesk.mappers.AreaMapper;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.services.IAreaService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/area")
public class AreaController {
    private final IAreaService areaService;
    private final AreaMapper areaMapper = AreaMapper.INSTANCE;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody AreaDTO areaDTO){
        areaService.findAreaByNameAndIdCampus(areaDTO.getName(), areaDTO.getIdCampus());
        areaDTO.setEnabled(true);
        Area area = areaService.save(areaMapper.convertToEntity(areaDTO));
        URI location = URI.create(String.format("/area/%d", area.getIdArea()));
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/campus/{idCampus}")
    public ResponseEntity<List<AreaDTO>> findAllById(@PathVariable("idCampus") Integer idCampus){
        List<AreaDTO> areas = areaService.findAllAreasByIdCampus(idCampus)
                .stream()
                .map(areaMapper::convertToDTO).toList();
        return ResponseEntity.ok(areas);
    }
}
