package ru.itmo.blss.firstlab;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.itmo.blss.firstlab.data.dto.UserDTO;
import ru.itmo.blss.firstlab.service.UserService;

@SpringBootApplication
public class FirstLabApplication {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(FirstLabApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            userService.newUser(new UserDTO("user", "user"));
            userService.newModerator(new UserDTO("moder", "moder"));
            userService.newAdvert(new UserDTO("advert", "advert"));
        };
    }
}
