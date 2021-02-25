package ru.itmo.blss.firstlab.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.blss.firstlab.data.dto.UserDTO;
import ru.itmo.blss.firstlab.data.entity.Role;
import ru.itmo.blss.firstlab.data.entity.User;
import ru.itmo.blss.firstlab.data.repository.RoleRepository;
import ru.itmo.blss.firstlab.data.repository.UsersRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;

    public User getById(int id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    public User newUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.login);
        user.setPassword(userDTO.password);

        Role role = roleRepository.findByName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        return usersRepository.save(user);
    }

    public void banUser(User user) {
        user.setBlocked(true);
        user.setWhenBlocked(LocalDateTime.now());
    }
}
