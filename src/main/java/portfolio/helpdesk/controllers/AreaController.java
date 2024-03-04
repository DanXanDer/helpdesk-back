package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.AreaCreationDTO;
import portfolio.helpdesk.DTO.response.AreaResponseDTO;
import portfolio.helpdesk.mappers.AreaMapper;
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
        areaService.findByNameAndIdBranch(areaCreationDTO.name(), areaCreationDTO.idBranch());
        AreaResponseDTO area = areaMapper
                .convertToDTO(areaService.save(areaMapper.convertToEntity(areaCreationDTO)));
        URI location = URI.create(String.format("/area/%d", area.idArea()));
        return ResponseEntity.created(location).build();
    }

    @Transactional
    @PatchMapping("/{idArea}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable("idArea") Integer idArea
    ) {
        areaService.updateStatusByIdArea(idArea);
        return ResponseEntity.ok().build();
    }
}
