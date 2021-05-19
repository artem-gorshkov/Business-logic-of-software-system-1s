package ru.itmo.blss.main.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.blss.main.service.CommentsService;

import java.security.Principal;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
@Api(tags = {"comments"}, description = "Управление комментариями")
public class CommentsController {

    private final CommentsService commentsService;

    @DeleteMapping("/{id}")
    @ApiOperation("Удалить комментарий")
    public void deleteComment(@PathVariable int id, Principal principal) {
        commentsService.deleteComment(id, principal.getName());
    }
}
