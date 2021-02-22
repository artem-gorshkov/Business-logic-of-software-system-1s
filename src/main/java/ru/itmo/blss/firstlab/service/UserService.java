package ru.itmo.blss.firstlab.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.blss.firstlab.data.repository.RoleRepository;
import ru.itmo.blss.firstlab.data.repository.UsersRepository;

@Service
@AllArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;

}
