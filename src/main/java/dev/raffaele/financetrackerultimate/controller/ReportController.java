package dev.raffaele.financetrackerultimate.controller;

import dev.raffaele.financetrackerultimate.dto.ReportDTO;
import dev.raffaele.financetrackerultimate.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports/")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDTO> generateReport(@PathVariable int id) {
            ReportDTO report = reportService.generateReport(id);
            return ResponseEntity.ok(report);
    }

}
