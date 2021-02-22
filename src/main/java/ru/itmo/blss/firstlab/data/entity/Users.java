package ru.itmo.blss.firstlab.data.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Users {
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
    @JoinColumn(name="author")
    private Set<Post> posts;

}
