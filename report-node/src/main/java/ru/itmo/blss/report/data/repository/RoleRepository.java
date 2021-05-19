package ru.itmo.blss.report.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.report.data.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);
}
