package ru.itmo.blss.firstlab.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.firstlab.data.entity.Status;

public interface StatusRepository extends CrudRepository<Status, Integer> {
}
