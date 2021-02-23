package ru.itmo.blss.firstlab.data.repository;

import org.springframework.data.repository.CrudRepository;
import ru.itmo.blss.firstlab.data.entity.Comment;
import ru.itmo.blss.firstlab.data.entity.Complain;

import java.util.List;

public interface ComplainRepository extends CrudRepository<Complain, Integer> {

    List<Complain> getAllByCommentId(int commentId);

    List<Complain> getAllByComment(Comment comment);

    Integer countComplainsByComment(Comment comment);
}
