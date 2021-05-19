package ru.itmo.blss.main.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.main.data.entity.Status;

public interface StatusRepository extends CrudRepository<Status, Integer> {

    Status getStatusByName(String name);
}
