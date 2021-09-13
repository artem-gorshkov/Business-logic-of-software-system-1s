package ru.itmo.blss.report.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.blss.report.data.entity.User;
import ru.itmo.blss.report.data.repository.RoleRepository;
import ru.itmo.blss.report.data.repository.UsersRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User getById(int id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    public void banUser(User user) {
        user.setBlocked(true);
        user.setWhenBlocked(LocalDateTime.now());
    }
}
