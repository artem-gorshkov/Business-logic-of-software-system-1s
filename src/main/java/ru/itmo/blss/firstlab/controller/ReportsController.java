package ru.itmo.blss.firstlab.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itmo.blss.firstlab.data.entity.Report;
import ru.itmo.blss.firstlab.service.ReportsService;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@AllArgsConstructor
public class ReportsController {
    private final ReportsService reportsService;

    @GetMapping
    public Iterable<Report> getAllReports() {
        return reportsService.getAllReports();
    }

    @GetMapping
    public List<Report> getPendingReports() {
        return reportsService.getPendingReports();
    }

    @PostMapping("/{id}/accept")
    public void acceptReport(@PathVariable int id) {
        reportsService.markReportAccepted(id);
    }

    @PostMapping("/{id}/reject")
    public void rejectReport(@PathVariable int id) {
        reportsService.markReportRejected(id);
    }
}
