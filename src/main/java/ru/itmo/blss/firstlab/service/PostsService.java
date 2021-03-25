package ru.itmo.blss.firstlab.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.itmo.blss.firstlab.data.dto.PostDTO;
import ru.itmo.blss.firstlab.data.entity.Post;
import ru.itmo.blss.firstlab.data.entity.Topic;
import ru.itmo.blss.firstlab.data.entity.User;
import ru.itmo.blss.firstlab.data.repository.PostRepository;
import ru.itmo.blss.firstlab.data.repository.TopicRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PostsService {
    private final PostRepository postRepository;
    private final TopicRepository topicRepository;
    private final UserService userService;

    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    public Post newPost(PostDTO postDTO, String login, boolean isPaid) {
        User user = userService.getByLogin(login);
        Post post = new Post();
        post.setAuthor(user);
        post.setTitle(postDTO.title);
        Topic topic = topicRepository.findByName(postDTO.topic)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Тема не найдена"));
        post.setTopic(topic);
        post.setPayload(postDTO.payload);
        post.setCreated(LocalDateTime.now());
        post.setPaid(isPaid);
        return postRepository.save(post);
    }

    public List<Post> getAllPaidPosts() {
        return postRepository.findAllByPaidIsTrue();
    }
}
