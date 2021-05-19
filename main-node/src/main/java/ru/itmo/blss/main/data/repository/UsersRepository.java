package ru.itmo.blss.main.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.itmo.blss.main.data.entity.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Integer> {

    Optional<User> findByLogin(String username);

    @Query("select u from User u left join u.roles as r where r.name=:roleName")
    List<User> findByRole(@Param("roleName") String roleName);

}
