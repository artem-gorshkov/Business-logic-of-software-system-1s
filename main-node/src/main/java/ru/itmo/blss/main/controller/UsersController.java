package ru.itmo.blss.main.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.blss.data.dto.UserDTO;
import ru.itmo.blss.main.data.entity.User;
import ru.itmo.blss.main.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Api(tags = {"users"}, description = "Управление пользователями")
public class UsersController {
    private final UserService userService;

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

    @GetMapping("/moderators")
    @ApiOperation("Получить список модераторов")
    public List<User> getModerators() {
        return userService.getModerators();
    }

    @PostMapping("/moderators")
    @ApiOperation("Создать модератора")
    public void addModerator(@RequestBody String login) {
        userService.addModerator(login);
    }
}
