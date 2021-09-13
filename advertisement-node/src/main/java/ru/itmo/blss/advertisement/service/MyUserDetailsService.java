package ru.itmo.blss.advertisement.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmo.blss.advertisement.config.MyUserPrincipal;
import ru.itmo.blss.advertisement.data.entity.User;
import ru.itmo.blss.advertisement.data.repository.UsersRepository;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new MyUserPrincipal(user);
    }
}
