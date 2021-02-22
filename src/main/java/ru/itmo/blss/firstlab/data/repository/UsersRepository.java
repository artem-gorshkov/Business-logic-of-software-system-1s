package ru.itmo.blss.firstlab.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.firstlab.data.entity.Users;

public interface UsersRepository extends CrudRepository<Users, Integer> {
}
