package ru.itmo.blss.main.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.itmo.blss.data.dto.ReportDto;
import ru.itmo.blss.main.data.entity.Comment;
import ru.itmo.blss.main.data.entity.Complain;
import ru.itmo.blss.main.data.entity.User;
import ru.itmo.blss.main.data.repository.ComplainRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class ComplainsService {
    public static final String REASON = "Нельзя жаловаться на свой комментарий";
    private final UserService userService;
    private final ComplainRepository complainRepository;
    private final CommentsService commentsService;
    private final StatusService statusService;
    private final KafkaService kafkaService;

    @Transactional
    public Complain newComplainForComment(int commentId, String payload, String login) {
        Complain complain = new Complain();
        Comment comment = commentsService.getCommentById(commentId);
        if (comment.getAuthor().getLogin().equals(login)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, REASON);
        }

        complain.setComment(comment);
        User author = userService.getByLogin(login);
        complain.setAuthor(author);
        complain.setPayload(payload);
        complain.setCreated(LocalDateTime.now());
        complain = complainRepository.save(complain);

        if (complainRepository.countComplainsByComment(comment) > 1) {
            kafkaService.send(createReportDto(comment));
        }

        return complain;
    }

    private ReportDto createReportDto(Comment comment) {
        ReportDto reportDto = new ReportDto();
        reportDto.setCommentId(comment.getId());
        reportDto.setStatusId(statusService.getSubmittedStatus().getId());
        return reportDto;
    }
}
