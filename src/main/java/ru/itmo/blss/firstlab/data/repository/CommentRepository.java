package ru.itmo.blss.firstlab.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.firstlab.data.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
