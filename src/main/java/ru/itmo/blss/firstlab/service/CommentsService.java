package ru.itmo.blss.firstlab.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.blss.firstlab.data.dto.CommentDTO;
import ru.itmo.blss.firstlab.data.entity.Comment;
import ru.itmo.blss.firstlab.data.entity.Post;
import ru.itmo.blss.firstlab.data.entity.User;
import ru.itmo.blss.firstlab.data.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentsService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostsService postsService;

    public List<Comment> getPostComments(Post post) {
        return commentRepository.findCommentsByPost(post);
    }

    public List<Comment> getPostComments(int postId) {
        return commentRepository.findCommentsByPostId(postId);
    }

    public Comment getCommentById(int id) {
        return commentRepository.findById(id).orElseThrow();
    }

    public Comment newCommentForPost(CommentDTO commentDTO, int postId) {
        Comment comment = new Comment();

        User user = userService.getById(commentDTO.authorId);
        comment.setAuthor(user);

        Post post = postsService.getPostById(postId);
        comment.setPost(post);

        comment.setPayload(commentDTO.payload);
        comment.setCreated(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public void deleteComment(int id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.setDeleted(true);
        commentRepository.save(comment);
    }
}
