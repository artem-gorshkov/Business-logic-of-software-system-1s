package ru.itmo.blss.advertisement.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.advertisement.data.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);
}
