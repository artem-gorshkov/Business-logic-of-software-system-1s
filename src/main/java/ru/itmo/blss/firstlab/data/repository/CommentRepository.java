package ru.itmo.blss.firstlab.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.firstlab.data.entity.Comment;
import ru.itmo.blss.firstlab.data.entity.Post;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    public Iterable<Comment> findCommentsByPost(Post post);
    public Iterable<Comment> findCommentsByPostId(int postId);
}
