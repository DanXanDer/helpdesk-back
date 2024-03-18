package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.AreaRequestDTO;
import portfolio.helpdesk.DTO.request.AreaUpdateDTO;
import portfolio.helpdesk.mappers.AreaMapper;
import portfolio.helpdesk.models.Area;
import portfolio.helpdesk.services.IAreaService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/areas")
public class AreaController {
    private final IAreaService areaService;
    private final AreaMapper areaMapper;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody AreaRequestDTO areaRequestDTO) {
        areaService.findByNameAndBranch(areaRequestDTO.getName(), areaRequestDTO.getIdBranch());
        Integer idArea = areaService.save(areaMapper.convertToEntity(areaRequestDTO)).getIdArea();
        URI location = URI.create(String.format("/area/%d", idArea));
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{idArea}/update")
    public ResponseEntity<Void> update(
            @PathVariable("idArea") Integer idArea,
            @Valid @RequestBody AreaUpdateDTO areaUpdateDTO
    ) {
        Area area = areaService.findById(idArea);
        if (areaUpdateDTO.name() != null) {
            areaService.findByNameAndBranch(areaUpdateDTO.name(), area.getBranch().getIdBranch());
        }
        areaMapper.updateFromDTO(areaUpdateDTO, area);
        areaService.save(area);
        return ResponseEntity.ok().build();
    }
}
