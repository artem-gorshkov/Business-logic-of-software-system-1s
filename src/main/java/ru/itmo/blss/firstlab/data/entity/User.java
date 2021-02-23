package ru.itmo.blss.firstlab.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String login;
    private String password;
    private boolean blocked;
    private LocalDateTime whenBlocked;
    @ManyToMany
    private Set<Role> roles;
    @OneToMany
    @JoinColumn(name = "author_id")
    private Set<Post> posts;

    //TODO: destroy infinite recursion for posts and roles

}
