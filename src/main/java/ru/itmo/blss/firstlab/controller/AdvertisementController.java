package ru.itmo.blss.firstlab.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itmo.blss.firstlab.data.dto.PostDTO;
import ru.itmo.blss.firstlab.data.entity.Post;
import ru.itmo.blss.firstlab.service.PostsService;

import java.security.Principal;

@RestController
@RequestMapping("/api/advert")
@AllArgsConstructor
@Api(tags = {"advertisements"}, description = "Управление рекламными постами")
public class AdvertisementController {
    private final PostsService postsService;

    @GetMapping
    @ApiOperation("Получить все рекламные посты")
    public Iterable<Post> getAllPaidPosts() {
        return postsService.getAllPaidPosts();
    }

    @PostMapping
    @ApiOperation("Создать новый рекламный пост")
    public Post newPaidPost(@RequestBody PostDTO postDTO, Principal principal) {
        return postsService.newPost(postDTO, principal.getName(), true);
    }
}
