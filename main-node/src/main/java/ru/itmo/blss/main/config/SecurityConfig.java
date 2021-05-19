package ru.itmo.blss.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // @formatter:off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic(withDefaults())
                .authorizeRequests((authorize) -> authorize
                        .antMatchers(HttpMethod.GET, "/api/users/", "/api/users/*", "/api/users/moderators").permitAll()
                        .antMatchers("/api/topics/**", "/api/users/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .csrf().disable()
                .formLogin(withDefaults());
    }
    // @formatter:on

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
