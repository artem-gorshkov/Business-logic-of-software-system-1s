package ru.itmo.blss.firstlab.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.blss.firstlab.data.dto.TopicDto;
import ru.itmo.blss.firstlab.data.entity.Report;
import ru.itmo.blss.firstlab.data.entity.Topic;
import ru.itmo.blss.firstlab.data.repository.TopicRepository;
import ru.itmo.blss.firstlab.service.ReportsService;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
@AllArgsConstructor
@Api(tags = {"topic"}, description = "Управление темами")
public class TopicController {
    private final TopicRepository topicRepository;

    @GetMapping
    @ApiOperation("Получить все темы")
    public Iterable<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @PostMapping
    @ApiOperation("Добавить топик")
    public void addTopic(@RequestBody TopicDto topicDto) {
        topicRepository.save(new Topic(topicDto.getName(), topicDto.getDescription()));
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удалить топик")
    public void deleteTopic(@PathVariable int id) {
        topicRepository.deleteById(id);
    }
}
