package ru.itmo.blss.advertisement.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.blss.advertisement.data.repository.RoleRepository;
import ru.itmo.blss.advertisement.data.repository.UsersRepository;
import ru.itmo.blss.data.dto.UserDTO;
import ru.itmo.blss.advertisement.data.entity.Role;
import ru.itmo.blss.advertisement.data.entity.User;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public User getByLogin(String login) {
        return usersRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(login)));
    }

    public List<User> getModerators() {
        return usersRepository.findByRole("ROLE_MODERATOR");
    }

    public void addModerator(String login) {
        User user = usersRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(login)));
        user.getRoles().add(roleRepository.findByName("ROLE_MODERATOR"));
        usersRepository.save(user);
    }

    public User newUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.login);
        user.setPassword(passwordEncoder.encode(userDTO.password));

        Role role = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        return usersRepository.save(user);
    }

    public void banUser(User user) {
        user.setBlocked(true);
        user.setWhenBlocked(LocalDateTime.now());
    }

    public User newModerator(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.login);
        user.setPassword(passwordEncoder.encode(userDTO.password));

        Role roleModerator = roleRepository.findByName("ROLE_MODERATOR");
        Role roleUser = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(roleModerator);
        roles.add(roleUser);
        user.setRoles(roles);

        return usersRepository.save(user);
    }

    public User newAdmin(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.login);
        user.setPassword(passwordEncoder.encode(userDTO.password));

        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        Role roleModerator = roleRepository.findByName("ROLE_MODERATOR");
        Role roleUser = roleRepository.findByName("ROLE_USER");
        Role roleAdvert = roleRepository.findByName("ROLE_ADVERT");
        Set<Role> roles = new HashSet<>();
        roles.add(roleAdmin);
        roles.add(roleModerator);
        roles.add(roleUser);
        roles.add(roleAdvert);
        user.setRoles(roles);

        return usersRepository.save(user);
    }

    public User newAdvert(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.login);
        user.setPassword(passwordEncoder.encode(userDTO.password));

        Set<Role> roles = new HashSet<>();
        Role roleAdvert = roleRepository.findByName("ROLE_ADVERT");
        Role roleUser = roleRepository.findByName("ROLE_USER");
        roles.add(roleAdvert);
        roles.add(roleUser);
        user.setRoles(roles);

        return usersRepository.save(user);
    }
}
