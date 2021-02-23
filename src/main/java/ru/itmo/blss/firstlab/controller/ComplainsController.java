package ru.itmo.blss.firstlab.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itmo.blss.firstlab.data.dto.ComplainDTO;
import ru.itmo.blss.firstlab.data.entity.Complain;
import ru.itmo.blss.firstlab.service.ComplainsService;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
@Api(tags = {"comments"})
public class ComplainsController {
    private final ComplainsService complainsService;

    @PostMapping("/{commentId}/complain")
    @ApiOperation("Пожаловаться на комментарий")
    public Complain complainForComment(@PathVariable int commentId, @RequestBody ComplainDTO complainDTO) {
        return complainsService.newComplainForComment(commentId, complainDTO);
    }
}
