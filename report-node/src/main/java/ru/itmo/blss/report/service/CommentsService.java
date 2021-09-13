package ru.itmo.blss.report.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.blss.report.data.entity.Comment;
import ru.itmo.blss.report.data.repository.CommentRepository;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class CommentsService {
    private final CommentRepository commentRepository;

    public Comment getCommentById(int id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    public void deleteComment(Comment comment) {
        comment.setDeleted(true);
        commentRepository.save(comment);
    }
}
