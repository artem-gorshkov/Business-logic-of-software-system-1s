package ru.itmo.blss.advertisement.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.blss.advertisement.data.entity.User;
import ru.itmo.blss.advertisement.data.repository.UsersRepository;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;

    public User getByLogin(String login) {
        return usersRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(login)));
    }
}
