package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.AreaCreationDTO;
import portfolio.helpdesk.DTO.response.AreaResponseDTO;
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
    public ResponseEntity<Void> save(@Valid @RequestBody AreaCreationDTO areaCreationDTO) {
        areaService.findAreaByNameAndIdBranch(areaCreationDTO.name(), areaCreationDTO.idBranch());
        Area area = areaService.save(areaMapper.convertToEntity(areaCreationDTO));
        URI location = URI.create(String.format("/area/%d", area.getIdArea()));
        return ResponseEntity.created(location).build();
    }

    @Transactional
    @PatchMapping("/{idArea}/status")
    public ResponseEntity<AreaResponseDTO> updateStatus(
            @PathVariable("idArea") Integer idArea
    ) {
        Area area = areaService.findById(idArea);
        boolean newStatus = !area.isEnabled();
        areaService.updateAreaStatusByIdArea(idArea, newStatus);
        return ResponseEntity.ok(areaMapper.convertToDTO(area));
    }
}
