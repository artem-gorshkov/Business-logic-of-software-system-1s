package ru.itmo.blss.firstlab.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.blss.firstlab.data.dto.UserDTO;
import ru.itmo.blss.firstlab.data.entity.Role;
import ru.itmo.blss.firstlab.data.entity.User;
import ru.itmo.blss.firstlab.data.repository.RoleRepository;
import ru.itmo.blss.firstlab.data.repository.UsersRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;

    public User getById(int id) {
        Optional<User> user = usersRepository.findById(id);
        if (user.isPresent()) return user.get();
        else throw new EntityNotFoundException(String.format("No user with id = %d", id));
    }

    public User newUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.login);
        user.setPassword(userDTO.password);

        Role role = roleRepository.findByName("USER");
        user.setRoles(Set.of(role));

        return usersRepository.save(user);
    }

}
