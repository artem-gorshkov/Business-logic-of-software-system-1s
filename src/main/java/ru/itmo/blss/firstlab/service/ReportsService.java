package ru.itmo.blss.firstlab.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.blss.firstlab.data.entity.Comment;
import ru.itmo.blss.firstlab.data.entity.Report;
import ru.itmo.blss.firstlab.data.repository.ReportRepository;

@Service
@AllArgsConstructor
public class ReportsService {
    private final StatusService statusService;
    private final ReportRepository reportRepository;

    public void newReport(Comment comment) {
        Report report = new Report();
        report.setComment(comment);
        report.setStatus(statusService.getSubmittedStatus());
        reportRepository.save(report);
    }
}
