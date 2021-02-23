package ru.itmo.blss.firstlab.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.firstlab.data.entity.Report;

public interface ReportRepository extends CrudRepository<Report, Integer> {

}
