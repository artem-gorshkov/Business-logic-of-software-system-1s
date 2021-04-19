package ru.itmo.blss.firstlab.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.blss.firstlab.data.entity.Complain;
import ru.itmo.blss.firstlab.service.ComplainsService;

import javax.transaction.SystemException;
import java.security.Principal;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
@Api(tags = {"comments"})
public class ComplainsController {
    private final ComplainsService complainsService;

    @PostMapping("/{commentId}/complain")
    @ApiOperation("Пожаловаться на комментарий")
    public Complain complainForComment(@PathVariable int commentId, @RequestBody String payload, Principal principal) throws SystemException {
        return complainsService.newComplainForComment(commentId, payload, principal.getName());
    }
}
