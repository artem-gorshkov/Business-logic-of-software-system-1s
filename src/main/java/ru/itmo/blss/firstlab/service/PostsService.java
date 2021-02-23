package ru.itmo.blss.firstlab.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.blss.firstlab.data.dto.PostDTO;
import ru.itmo.blss.firstlab.data.entity.Post;
import ru.itmo.blss.firstlab.data.entity.User;
import ru.itmo.blss.firstlab.data.repository.PostRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PostsService {
    private final PostRepository postRepository;
    private final UserService userService;

    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
         return postRepository.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    public Post newPost(PostDTO postDTO) {
        User user = userService.getById(postDTO.authorId);
        Post post = new Post();
        post.setAuthor(user);
        post.setTitle(postDTO.title);
        post.setPayload(postDTO.payload);
        post.setCreated(LocalDateTime.now());
        return postRepository.save(post);
    }
}
