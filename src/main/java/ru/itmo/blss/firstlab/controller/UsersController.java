package ru.itmo.blss.firstlab.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itmo.blss.firstlab.data.dto.UserDTO;
import ru.itmo.blss.firstlab.data.entity.User;
import ru.itmo.blss.firstlab.service.UserService;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UsersController {
    private final UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getById(id);
    }

    @PostMapping
    public User newUser(@RequestBody UserDTO userDTO) {
        return userService.newUser(userDTO);
    }
}
