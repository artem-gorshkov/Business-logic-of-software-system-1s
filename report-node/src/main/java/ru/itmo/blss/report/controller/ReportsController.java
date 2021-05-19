package ru.itmo.blss.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itmo.blss.report.data.entity.Report;
import ru.itmo.blss.report.service.ReportsService;

import javax.transaction.SystemException;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@AllArgsConstructor
@Api(tags = {"reports"}, description = "Управление репортами")
public class ReportsController {
    private final ReportsService reportsService;

    @GetMapping("user/{id}")
    @ApiOperation("Получить репорты на пользователя")
    public List<Report> getUserReports(@PathVariable int id, @RequestParam(required = false) Boolean accepted) {
        if (accepted != null) return reportsService.getUserReports(id, accepted);
        return reportsService.getUserReports(id);
    }

    @GetMapping
    @ApiOperation("Получить все репорты")
    public Iterable<Report> getAllReports() {
        return reportsService.getAllReports();
    }

    @GetMapping("/active")
    @ApiOperation("Получить все активные репорты")
    public List<Report> getPendingReports() {
        return reportsService.getPendingReports();
    }

    @PostMapping("/{id}/accept")
    @ApiOperation("Принять репорт")
    public void acceptReport(@PathVariable int id) throws SystemException {
        reportsService.markReportAccepted(id);
    }

    @PostMapping("/{id}/reject")
    @ApiOperation("Отклонить репорт")
    public void rejectReport(@PathVariable int id) throws SystemException {
        reportsService.markReportRejected(id);
    }
}
