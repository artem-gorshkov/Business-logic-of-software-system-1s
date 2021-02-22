package ru.itmo.blss.firstlab.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.firstlab.data.entity.Complain;

public interface ComplainRepository extends CrudRepository<Complain, Integer> {
}
