package ru.itmo.blss.main.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.main.data.entity.Comment;
import ru.itmo.blss.main.data.entity.Post;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    List<Comment> findCommentsByPost(Post post);

    List<Comment> findCommentsByPostId(int postId);
}
