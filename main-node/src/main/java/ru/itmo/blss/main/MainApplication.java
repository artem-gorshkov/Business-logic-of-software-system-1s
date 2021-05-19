package ru.itmo.blss.main;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.itmo.blss.data.dto.UserDTO;
import ru.itmo.blss.main.service.UserService;

@SpringBootApplication
public class MainApplication {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    InitializingBean sendDatabase() {
        return () -> {
            userService.newUser(new UserDTO("user", "user"));
            userService.newModerator(new UserDTO("moder", "moder"));
            userService.newAdvert(new UserDTO("advert", "advert"));
            userService.newAdmin(new UserDTO("admin", "admin"));
        };
    }
}
