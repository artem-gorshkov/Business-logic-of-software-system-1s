package ru.itmo.blss.firstlab.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.blss.firstlab.data.entity.Comment;
import ru.itmo.blss.firstlab.data.entity.Report;
import ru.itmo.blss.firstlab.data.entity.Status;
import ru.itmo.blss.firstlab.data.entity.User;
import ru.itmo.blss.firstlab.data.repository.ReportRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportsService {
    private final UserService userService;
    private final StatusService statusService;
    private final ReportRepository reportRepository;
    private final CommentsService commentsService;
    private final UserTransaction userTransaction;

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

    public void markReportRejected(int reportId) throws SystemException {
        try {
            userTransaction.begin();
            Report report = reportRepository.findById(reportId)
                    .orElseThrow(() -> new EntityNotFoundException(String.valueOf(reportId)));
            report.setStatus(statusService.getRejectedStatus());
            reportRepository.save(report);
            userTransaction.commit();
        } catch (Exception e) {
            userTransaction.rollback();
        }
    }

    public void markReportAccepted(int reportId) throws SystemException {
        try {
            userTransaction.begin();
            Report report = reportRepository.findById(reportId)
                    .orElseThrow(() -> new EntityNotFoundException(String.valueOf(reportId)));
            Comment comment = report.getComment();
            commentsService.deleteComment(comment);

            User commentAuthor = comment.getAuthor();
            Status acceptedStatus = statusService.getAcceptedStatus();
            if (reportRepository.countReportsByCommentAuthorAndStatus(commentAuthor, acceptedStatus) >= 1) {
                userService.banUser(comment.getAuthor());
            }

            report.setStatus(acceptedStatus);
            reportRepository.save(report);
        } catch (Exception e) {
            userTransaction.rollback();
        }
    }

    public List<Report> getUserReports(int userId, boolean accepted) {
        User user = userService.getById(userId);
        Status status;
        if (accepted) {
            status = statusService.getAcceptedStatus();
        } else status = statusService.getRejectedStatus();
        return reportRepository.getAllByCommentAuthorAndStatus(user, status);
    }

    public List<Report> getUserReports(int userId) {
        User user = userService.getById(userId);
        return reportRepository.getAllByCommentAuthor(user);
    }
}
