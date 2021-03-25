package ru.itmo.blss.firstlab.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itmo.blss.firstlab.data.dto.PostDTO;
import ru.itmo.blss.firstlab.data.entity.Comment;
import ru.itmo.blss.firstlab.data.entity.Post;
import ru.itmo.blss.firstlab.service.CommentsService;
import ru.itmo.blss.firstlab.service.PostsService;

import java.security.Principal;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
@Api(tags = {"posts"}, description = "Управление постами")
public class PostsController {
    private final PostsService postsService;
    private final CommentsService commentsService;

    @GetMapping
    @ApiOperation("Получить все посты")
    public Iterable<Post> getAllPosts() {
        return postsService.getAllPosts();
    }

    @PostMapping
    @ApiOperation("Создать новый пост")
    public Post newPost(@RequestBody PostDTO postDTO, Principal principal) {
        return postsService.newPost(postDTO, principal.getName(), false);
    }

    @GetMapping("/{id}")
    @ApiOperation("Получить пост по id")
    public Post getPost(@PathVariable int id) {
        return postsService.getPostById(id);
    }

    @GetMapping("/{id}/comments")
    @ApiOperation("Получить комментарии по id поста")
    public Iterable<Comment> getCommentsForPost(@PathVariable int id) {
        return commentsService.getPostComments(id);
    }

    @PostMapping("/{id}/comments")
    @ApiOperation("Добавить комментарий к посту")
    public Comment addCommentForPost(@PathVariable int id, @RequestBody String comment, Principal principal) {
        return commentsService.newCommentForPost(comment, id, principal.getName());
    }
}
