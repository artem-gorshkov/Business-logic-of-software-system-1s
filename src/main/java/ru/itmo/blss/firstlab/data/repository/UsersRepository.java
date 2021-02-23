package ru.itmo.blss.firstlab.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.firstlab.data.entity.User;

public interface UsersRepository extends CrudRepository<User, Integer> {
}
