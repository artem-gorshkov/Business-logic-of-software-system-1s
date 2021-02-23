package ru.itmo.blss.firstlab.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itmo.blss.firstlab.data.dto.UserDTO;
import ru.itmo.blss.firstlab.data.entity.Report;
import ru.itmo.blss.firstlab.data.entity.User;
import ru.itmo.blss.firstlab.service.ReportsService;
import ru.itmo.blss.firstlab.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Api(tags = {"users"}, description = "Управление пользователями")
public class UsersController {
    private final UserService userService;
    private final ReportsService reportsService;

    @GetMapping("/{id}")
    @ApiOperation("Получить пользователя по id")
    public User getUserById(@PathVariable int id) {
        return userService.getById(id);
    }

    @PostMapping
    @ApiOperation("Создать пользователя")
    public User newUser(@RequestBody UserDTO userDTO) {
        return userService.newUser(userDTO);
    }

    @GetMapping("/{id}/reports")
    @ApiOperation("Получить репорты на пользователя")
    public List<Report> getUserReports(@PathVariable int id, @RequestParam(required = false) Boolean accepted) {
        if (accepted != null) return reportsService.getUserReports(id, accepted);
        return reportsService.getUserReports(id);
    }
}
