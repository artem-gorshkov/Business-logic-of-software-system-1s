package ru.itmo.blss.advertisement.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.blss.data.dto.PostDTO;

import java.nio.charset.StandardCharsets;
import java.util.Random;

@Service
@AllArgsConstructor
public class PostAdvertService {
    private static int count = 1;
    private final PostsService postsService;

    public void execute() {
        postsService.newPost(createPostDTO(), "scheduled", true);
        count++;
    }

    private PostDTO createPostDTO() {
        byte[] array = new byte[20];
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        return new PostDTO(
                String.format("Пост, написанный компьютером №%d", count),
                "посты от компьютера",
                generatedString);
    }
}
