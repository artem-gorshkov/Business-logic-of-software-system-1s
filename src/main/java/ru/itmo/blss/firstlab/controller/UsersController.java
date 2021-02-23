package ru.itmo.blss.firstlab.controller;

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
public class UsersController {
    private final UserService userService;
    private final ReportsService reportsService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getById(id);
    }

    @PostMapping
    public User newUser(@RequestBody UserDTO userDTO) {
        return userService.newUser(userDTO);
    }

    @GetMapping("/{id}/reports")
    public List<Report> getUserReports(@PathVariable int id, @RequestParam(required = false) Boolean accepted) {
        if (accepted != null) return reportsService.getUserReports(id, accepted);
        return reportsService.getUserReports(id);
    }
}
