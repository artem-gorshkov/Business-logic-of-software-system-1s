package ru.itmo.blss.firstlab.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.firstlab.data.entity.Report;
import ru.itmo.blss.firstlab.data.entity.Status;
import ru.itmo.blss.firstlab.data.entity.User;

public interface ReportRepository extends CrudRepository<Report, Integer> {

    Integer countReportsByCommentAuthorAndStatus(User author, Status status);
}
