package ru.itmo.blss.advertisement.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.advertisement.data.entity.Post;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {
    List<Post> findAllByIsPaidTrue();
}
