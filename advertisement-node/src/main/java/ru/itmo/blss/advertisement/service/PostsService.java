package ru.itmo.blss.advertisement.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.itmo.blss.advertisement.data.entity.Post;
import ru.itmo.blss.advertisement.data.entity.Topic;
import ru.itmo.blss.advertisement.data.entity.User;
import ru.itmo.blss.advertisement.data.repository.PostRepository;
import ru.itmo.blss.advertisement.data.repository.TopicRepository;
import ru.itmo.blss.data.dto.PostDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PostsService {
    public static final String REASON = "Тема не найдена";
    private final PostRepository postRepository;
    private final TopicRepository topicRepository;
    private final UserService userService;

    public Post newPost(PostDTO postDTO, String login, boolean isPaid) {
        User user = userService.getByLogin(login);
        Post post = new Post();
        post.setAuthor(user);
        post.setTitle(postDTO.title);
        Topic topic = topicRepository.findByName(postDTO.topic)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, REASON));
        post.setTopic(topic);
        post.setPayload(postDTO.payload);
        post.setCreated(LocalDateTime.now());
        post.setPaid(isPaid);
        return postRepository.save(post);
    }

    public List<Post> getAllPaidPosts() {
        return postRepository.findAllByIsPaidTrue();
    }
}
