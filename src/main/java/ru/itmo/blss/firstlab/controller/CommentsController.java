package ru.itmo.blss.firstlab.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.blss.firstlab.service.CommentsService;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable int id) {
        commentsService.deleteComment(id);
    }
}
