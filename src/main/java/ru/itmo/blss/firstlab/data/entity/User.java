package ru.itmo.blss.firstlab.data.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String login;
    @JsonIgnore
    private String password;
    private boolean blocked;
    private LocalDateTime whenBlocked;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Set<Post> posts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return blocked == user.blocked &&
                id.equals(user.id) &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                Objects.equals(whenBlocked, user.whenBlocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, blocked, whenBlocked);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", blocked=" + blocked +
                ", whenBlocked=" + whenBlocked +
                '}';
    }
}
