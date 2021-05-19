package ru.itmo.blss.report.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.report.data.entity.Report;
import ru.itmo.blss.report.data.entity.Status;
import ru.itmo.blss.report.data.entity.User;

import java.util.List;

public interface ReportRepository extends CrudRepository<Report, Integer> {

    Integer countReportsByCommentAuthorAndStatus(User author, Status status);

    List<Report> getAllByStatus(Status status);

    List<Report> getAllByCommentAuthor(User author);

    List<Report> getAllByCommentAuthorAndStatus(User author, Status status);
}
