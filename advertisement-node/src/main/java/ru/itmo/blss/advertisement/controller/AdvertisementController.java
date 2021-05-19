package ru.itmo.blss.advertisement.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.blss.advertisement.service.PostsService;
import ru.itmo.blss.data.dto.PostDTO;
import ru.itmo.blss.advertisement.data.entity.Post;

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
