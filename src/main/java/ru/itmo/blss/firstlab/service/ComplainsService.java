package ru.itmo.blss.firstlab.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.blss.firstlab.data.dto.ComplainDTO;
import ru.itmo.blss.firstlab.data.entity.Comment;
import ru.itmo.blss.firstlab.data.entity.Complain;
import ru.itmo.blss.firstlab.data.entity.Status;
import ru.itmo.blss.firstlab.data.entity.User;
import ru.itmo.blss.firstlab.data.repository.ComplainRepository;
import ru.itmo.blss.firstlab.data.repository.StatusRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ComplainsService {
    private final UserService userService;
    private final ComplainRepository complainRepository;
    private final StatusRepository statusRepository;
    private final CommentsService commentsService;

    public Complain getComplainById(int id) {
        return complainRepository.findById(id).orElseThrow();
    }

    public Complain newComplainForComment(int commentId, ComplainDTO complainDTO) {
        Complain complain = new Complain();
        Status status = statusRepository.getStatusByName("Submitted");
        complain.setStatus(status);

        Comment comment = commentsService.getCommentById(commentId);
        complain.setComment(comment);

        User author = userService.getById(complainDTO.authorId);
        complain.setAuthor(author);

        complain.setPayload(complainDTO.payload);
        complain.setCreated(LocalDateTime.now());
        return complainRepository.save(complain);
    }

    public void deleteCommentComplains(Comment comment) {
        Iterable<Complain> complains = complainRepository.getAllByComment(comment);
        complains.forEach(complainRepository::delete);
    }
}
