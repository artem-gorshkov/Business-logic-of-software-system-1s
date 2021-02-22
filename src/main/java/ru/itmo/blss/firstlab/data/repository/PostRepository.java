package ru.itmo.blss.firstlab.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.firstlab.data.entity.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {
}
