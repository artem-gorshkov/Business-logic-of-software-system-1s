package ru.itmo.blss.firstlab.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.firstlab.data.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    public Role findByName(String name);
}
