package ru.itmo.blss.firstlab.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.blss.firstlab.data.dto.ComplainDTO;
import ru.itmo.blss.firstlab.data.entity.Comment;
import ru.itmo.blss.firstlab.data.entity.Complain;
import ru.itmo.blss.firstlab.data.entity.User;
import ru.itmo.blss.firstlab.data.repository.ComplainRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ComplainsService {
    private final UserService userService;
    private final ComplainRepository complainRepository;
    private final CommentsService commentsService;
    private final ReportsService reportsService;

    public Complain getComplainById(int id) {
        return complainRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    public Complain newComplainForComment(int commentId, ComplainDTO complainDTO) {
        Complain complain = new Complain();

        Comment comment = commentsService.getCommentById(commentId);
        complain.setComment(comment);

        User author = userService.getById(complainDTO.authorId);
        complain.setAuthor(author);

        complain.setPayload(complainDTO.payload);
        complain.setCreated(LocalDateTime.now());
        complain = complainRepository.save(complain);

        if (complainRepository.countComplainsByComment(comment) >= 5) {
            reportsService.newReport(comment);
        }

        return complain;
    }

    public void deleteCommentComplains(Comment comment) {
        Iterable<Complain> complains = complainRepository.getAllByComment(comment);
        complains.forEach(complainRepository::delete);
    }
}
