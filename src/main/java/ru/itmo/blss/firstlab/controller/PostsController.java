package ru.itmo.blss.firstlab.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itmo.blss.firstlab.data.dto.PostDTO;
import ru.itmo.blss.firstlab.data.entity.Post;
import ru.itmo.blss.firstlab.service.PostsService;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostsController {
    private final PostsService postsService;

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
}
