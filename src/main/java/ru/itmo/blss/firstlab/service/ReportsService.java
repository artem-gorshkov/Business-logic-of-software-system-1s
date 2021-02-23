package ru.itmo.blss.firstlab.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.blss.firstlab.data.entity.Comment;
import ru.itmo.blss.firstlab.data.entity.Report;
import ru.itmo.blss.firstlab.data.entity.Status;
import ru.itmo.blss.firstlab.data.entity.User;
import ru.itmo.blss.firstlab.data.repository.ReportRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ReportsService {
    private final UserService userService;
    private final StatusService statusService;
    private final ReportRepository reportRepository;
    private final CommentsService commentsService;

    public Iterable<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public List<Report> getPendingReports() {
        Status submittedStatus = statusService.getSubmittedStatus();
        return reportRepository.getAllByStatus(submittedStatus);
    }

    public void newReport(Comment comment) {
        Report report = new Report();
        report.setComment(comment);
        report.setStatus(statusService.getSubmittedStatus());
        reportRepository.save(report);
    }

    public void markReportRejected(int reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow();
        report.setStatus(statusService.getRejectedStatus());
        reportRepository.save(report);
    }

    public void markReportAccepted(int reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow();
        Comment comment = report.getComment();
        commentsService.deleteComment(comment);

        User commentAuthor = comment.getAuthor();
        Status acceptedStatus = statusService.getAcceptedStatus();
        if (reportRepository.countReportsByCommentAuthorAndStatus(commentAuthor, acceptedStatus) > 1) {
            userService.banUser(comment.getAuthor());
        }

        report.setStatus(acceptedStatus);
        reportRepository.save(report);
    }
}
