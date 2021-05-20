package ru.itmo.blss.report.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.report.data.entity.Status;

public interface StatusRepository extends CrudRepository<Status, Integer> {

    Status getStatusByName(String name);

    Status getStatusById(Integer id);
}
