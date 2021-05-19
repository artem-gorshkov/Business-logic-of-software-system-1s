package ru.itmo.blss.main.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.main.data.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);
}
