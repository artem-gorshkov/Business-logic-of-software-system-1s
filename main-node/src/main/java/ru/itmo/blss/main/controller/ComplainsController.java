package ru.itmo.blss.main.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itmo.blss.main.data.entity.Complain;
import ru.itmo.blss.main.service.ComplainsService;

import java.security.Principal;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
@Api(tags = {"comments"})
public class ComplainsController {
    private final ComplainsService complainsService;

    @PostMapping("/{commentId}/complain")
    @ApiOperation("Пожаловаться на комментарий")
    public Complain complainForComment(@PathVariable int commentId,
                                       @RequestBody String payload,
                                       Principal principal) {
        return complainsService.newComplainForComment(commentId, payload, principal.getName());
    }
}
