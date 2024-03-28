package portfolio.helpdesk.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import portfolio.helpdesk.DTO.request.ReportRequestDTO;
import portfolio.helpdesk.DTO.response.ReportResponseDTO;
import portfolio.helpdesk.mappers.ReportMapper;
import portfolio.helpdesk.services.IReportService;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reports")
public class ReportController {
    private final IReportService reportService;
    private final ReportMapper reportMapper;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid ReportRequestDTO report) {
        Integer id = reportService.save(reportMapper.convertToEntity(report)).getIdReport();
        URI location = URI.create(String.format("/reports/%s", id));
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<ReportResponseDTO>> findAll() {
        List<ReportResponseDTO> reports = reportService.findAll().stream().map(reportMapper::convertToDTO).toList();
        return ResponseEntity.ok(reports);
    }

}
