package ru.itmo.blss.firstlab.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itmo.blss.firstlab.data.dto.CommentDTO;
import ru.itmo.blss.firstlab.data.dto.PostDTO;
import ru.itmo.blss.firstlab.data.entity.Comment;
import ru.itmo.blss.firstlab.data.entity.Post;
import ru.itmo.blss.firstlab.service.CommentsService;
import ru.itmo.blss.firstlab.service.PostsService;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostsController {
    private final PostsService postsService;
    private final CommentsService commentsService;

    @GetMapping
    public Iterable<Post> getAllPosts() {
        return postsService.getAllPosts();
    }

    @PostMapping
    public Post newPost(@RequestBody PostDTO postDTO) {
        return postsService.newPost(postDTO);
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable int id) {
        return postsService.getPostById(id);
    }

    @GetMapping("/{id}/comments")
    public Iterable<Comment> getCommentsForPost(@PathVariable int id) {
        return commentsService.getPostComments(id);
    }

    @PostMapping("/{id}/comments")
    public Comment addCommentForPost(@PathVariable int id, @RequestBody CommentDTO commentDTO) {
        return commentsService.newCommentForPost(commentDTO, id);
    }
}
