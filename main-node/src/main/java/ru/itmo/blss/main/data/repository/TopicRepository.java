package ru.itmo.blss.main.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.main.data.entity.Topic;

import java.util.Optional;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
    Optional<Topic> findByName(String name);
}
