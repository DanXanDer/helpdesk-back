package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.helpdesk.DTO.AreaDTO;
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
    public ResponseEntity<Void> save(@Valid @RequestBody AreaDTO areaDTO){
        areaService.findAreaByNameAndIdCampus(areaDTO.getName(), areaDTO.getIdCampus());
        areaDTO.setEnabled(true);
        Area area = areaService.save(areaMapper.convertToEntity(areaDTO));
        URI location = URI.create(String.format("/area/%d", area.getIdArea()));
        return ResponseEntity.created(location).build();
    }
}
